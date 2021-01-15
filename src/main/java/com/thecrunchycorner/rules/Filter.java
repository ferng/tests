package com.thecrunchycorner.rules;

import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Filter {
    public Set<Integer> apply(HashMap<Integer, Integer> products, List<Size> sizes,
                              List<Stock> stocks) {
        Set<Integer> inStockSizes = getInStockSizes(stocks);
        Set<Integer> validSizes = new HashSet<>(inStockSizes);

        Set<Integer> backSoonSizes = getBackSoonSizes(sizes);
        validSizes.addAll(backSoonSizes);

        Set<Integer> invalidSpecialsId = getInvalidSpecials(validSizes, sizes);


        Set<Integer> validProductIds = products.keySet();
        validProductIds.removeAll(invalidSpecialsId);

        return sortedProducts(validProductIds, products);
    }

    LinkedHashSet<Integer> sortedProducts(Set<Integer> validProductIds,
                                          HashMap<Integer, Integer> products) {
        TreeMap<Integer, Integer> reducedProducts = new TreeMap<>();

        for (Integer id : validProductIds) {
            reducedProducts.put(products.get(id), id);
        }

        return new LinkedHashSet<>(reducedProducts.values());
    }


    Set<Integer> getBackSoonSizes(List<Size> sizes) {
        return sizes.stream()
                .filter(RuleProcessor.backSoonFilter)
                .map(Size::getId)
                .collect(Collectors.toSet());
    }


    Set<Integer> getInStockSizes(List<Stock> stocks) {
        return stocks.stream()
                .filter(RuleProcessor.inStockFilter)
                .map(Stock::getSizeId)
                .collect(Collectors.toSet());
    }


    Set<Integer> getInvalidSpecials(Set<Integer> validSizes, List<Size> sizes) {
        Map<Integer, List<Size>> sizesByProduct =
                sizes.stream()
                        .collect(
                                Collectors.groupingBy(Size::getProductId, Collectors.toList())
                        );

        HashSet<Integer> toRemove = new HashSet<>();

        for (Map.Entry<Integer, List<Size>> group : sizesByProduct.entrySet()) {
            List<Size> sizeGroup = group.getValue();

            Optional<Integer> invalidSpecial = isProductSpecialAndInStock(sizeGroup , validSizes);
            invalidSpecial.ifPresent(toRemove::add);
        }
        return toRemove;
    }


    Optional<Integer> isProductSpecialAndInStock(List<Size> sizeGroup , Set<Integer> validSizes) {
        boolean special = false;
        boolean inStock = false;
        int currentProductId = -1;

        for (Size size : sizeGroup) {
            currentProductId = size.getProductId();

            if (size.isSpecial()) {
                special = true;
            }
            if (validSizes.contains(size.getId()) && !size.isSpecial()) {
                inStock = true;
            }
        }

        if ((special && !inStock)) {
            return Optional.of(currentProductId);
        } else {
            return Optional.empty();
        }
    }
}