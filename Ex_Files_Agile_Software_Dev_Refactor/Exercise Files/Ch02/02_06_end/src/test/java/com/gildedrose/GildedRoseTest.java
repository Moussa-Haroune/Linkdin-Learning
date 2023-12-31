package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseTest {

    private Item createAndUpdate(int sellIn, int quality) {
        Item[] items = new Item[] { new Item("foo", sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    @Test
    public void testFrameworkWorks() {
        Item item = createAndUpdate(0, 0);
        assertEquals("foo", item.name);
    }

    @Test
    public void systemLowersValues() {
        Item item = createAndUpdate(15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }

    @Test
    public void qualityDegradesTwiceAsFast() {
        Item item = createAndUpdate(0, 17);
        assertEquals(15, item.quality);
    }

    @Test
    public void qualityIsNeverNegative() {
        Item item = createAndUpdate(0, 0);
        assertEquals(0, item.quality);
    }
}
