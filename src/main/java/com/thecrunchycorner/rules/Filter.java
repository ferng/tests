package com.thecrunchycorner.rules;

import com.thecrunchycorner.models.Size;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Filter {
    public Set<Integer> apply(HashMap<Integer, Integer> products, List<Size> sizes,
                              HashMap<Integer, Integer> stocks) {

        Set<Integer> validOptions = new HashSet<>(allowBackSoon(sizes));
        Set<Integer> inStock = inStockItems(stocks);
        validOptions.removeAll(removeInvalidSpecials(inStock, sizes));

        TreeMap<Integer, Integer> reducedProducts = new TreeMap<>();


        for (Integer id : validOptions) {
            reducedProducts.put(products.get(id), id);
        }

        return new LinkedHashSet<>(reducedProducts.values());
    }


    public Set<Integer> allowBackSoon(List<Size> sizes) {
        return sizes.stream()
                .filter(RuleProcessor.backSoonFilter)
                .map(Size::getProductId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> inStockItems(HashMap<Integer, Integer> stocks) {
        Set<Integer> inStock = new HashSet<>();

        for (Map.Entry<Integer, Integer> entry : stocks.entrySet()) {
            if (entry.getValue() > 0) {
                inStock.add(entry.getKey());
            }
        }
        return inStock;
    }


    public Set<Integer> removeInvalidSpecials(
            Set<Integer> inStockItems, List<Size> sizes) {
        HashSet<Integer> toRemove = new HashSet<>();

        Map<Integer, List<Size>> groupedSizes =
                sizes.stream()
                        .collect(
                                Collectors.groupingBy(Size::getProductId, Collectors.toList()));

        for (Map.Entry<Integer, List<Size>> group : groupedSizes.entrySet()) {
            List<Size> sizeGroup = group.getValue();

            boolean special = false;
            boolean inStock = false;
            int currentProductId = -1;

            for (Size size : sizeGroup) {
                currentProductId = size.getProductId();

                if (size.isSpecial()) {
                    special = true;
                }
                if (inStockItems.contains(size.getId()) && !size.isSpecial()) {
                    inStock = true;
                }


            }

            if ((special && !inStock)) {
                toRemove.add(currentProductId);
            }

        }
        return toRemove;


    }

}