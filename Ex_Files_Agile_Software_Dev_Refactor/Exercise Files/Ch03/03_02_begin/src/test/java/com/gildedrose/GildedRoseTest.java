package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseTest {

    private Item createAndUpdate(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    @Test
    public void testFrameworkWorks() {
        Item item = createAndUpdate("foo", 0, 0);
        assertEquals("foo", item.name);
    }

    @Test
    public void systemLowersValues() {
        Item item = createAndUpdate("foo", 15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }

    @Test
    public void qualityDegradesTwiceAsFast() {
        Item item = createAndUpdate("foo", 0, 17);
        assertEquals(15, item.quality);
    }

    @Test
    public void qualityIsNeverNegative() {
        Item item = createAndUpdate("foo", 0, 0);
        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieIncreasesInQuality() {
        Item item = createAndUpdate(GildedRose.AGED_BRIE, 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    public void qualityNeverMoreThan50() {
        Item item = createAndUpdate("foo", 15, 52);
        assertEquals(51, item.quality); // can exceed 50 if input exceeds 50

        item = createAndUpdate(GildedRose.AGED_BRIE, 15, 50);
        assertEquals(50, item.quality);
    }

    @Test
    public void sulfurasNeverHasToBeSoldOrDecreasesInQuality() {
        Item item = createAndUpdate("Sulfuras, Hand of Ragnaros", 1, 42);
        assertEquals(1, item.sellIn); // sellIn doesn't change
        assertEquals(42, item.quality); // quality doesn't change
    }

    @Test
    public void backstagePassesIncreaseInQuality() {
        Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    public void backstagePassesIncreaseBy2() {
        Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 25);
        assertEquals(27, item.quality);
    }

    @Test
    public void backstagePassesIncreaseBy3() {
        Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 5, 25);
        assertEquals(28, item.quality);
    }

    @Test
    public void backstagePassesQualityDropTo0() {
        Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 0, 25);
        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieNeverExpires() {
        Item item = createAndUpdate(GildedRose.AGED_BRIE, 0, 42);
        assertEquals(-1, item.sellIn);
        assertEquals(44, item.quality);
    }

    @Test
    public void backstagePassMaximumQuality() {
        Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 48);
        assertEquals(50, item.quality);

        item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        assertEquals(50, item.quality);

        item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        assertEquals(50, item.quality);
    }

    @Test
    public void degradeInQualityUnlessSulfuras() {
        Item item = createAndUpdate("foo", -1,1);
        assertEquals(0, item.quality);

        item = createAndUpdate("Sulfuras, Hand of Ragnaros", -1,1);
        assertEquals(1, item.quality);
    }

    @Test
    public void agedBrieMaximumQuality() {
        Item item = createAndUpdate(GildedRose.AGED_BRIE, -1, 49);
        assertEquals(50, item.quality);
    }
}
