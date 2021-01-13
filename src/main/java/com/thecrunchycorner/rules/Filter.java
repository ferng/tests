package com.thecrunchycorner.rules;

import com.thecrunchycorner.models.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Filter {
    public Set<Integer> apply(HashMap<Integer, Integer> products, List<Size> sizes,
                              HashMap<Integer, Integer> stocks) {

        Set<Integer> validOptions = new HashSet<>();

        validOptions.addAll(allowBackSoon(sizes));
        Set<Integer> inStock = inStockItems(stocks);
        validOptions.removeAll(removeInvalidSpecials(products, inStock, sizes));

        TreeMap<Integer, Integer> reducedProducts = new TreeMap<>();


        for(Integer id : validOptions) {
            reducedProducts.put(products.get(id), id);
        }


        return validOptions;
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


    public Set<Integer> removeInvalidSpecials(HashMap<Integer, Integer> products,
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
            int currentProductId =-1;

            for (Size size : sizeGroup) {
                currentProductId = size.getProductId();

                if (size.isSpecial()) {
                    special = true;
                }
                if (inStockItems.contains(size.getId())  && !size.isSpecial()) {
                    inStock = true;
                }


            }

            if ((special && !inStock)) {
                toRemove.add(currentProductId);
            }

        }
        return toRemove;


    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}