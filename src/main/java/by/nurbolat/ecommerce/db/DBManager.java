package by.nurbolat.ecommerce.db;

import by.nurbolat.ecommerce.entity.Item;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBManager {

    private static long incrementId = 5L;
    @Getter
    private static List<Item> items = new ArrayList<>();

    static {
        items.add(new Item(1L,"Bottle","Shaiker for bottle",2000.0));
        items.add(new Item(2L,"Carpet","Red carpet for bathroom",3800.0));
        items.add(new Item(3L,"Book","Roman of Abay",5600.0));
        items.add(new Item(4L,"iPhone","Smart Phone with AI",780_000.0));
    }

    public static void addItem(Item item){
        items.add(Item.builder()
                .id(incrementId)
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build());
        incrementId++;
    }


    public static Item getItemById(int itemId) {
       return items.stream().filter(item -> item.getId() == itemId).findFirst().get();
    }
}
