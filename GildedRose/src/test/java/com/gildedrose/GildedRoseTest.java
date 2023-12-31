package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

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
    public void  systemLowersValues(){
        Item item = createAndUpdate("foo", 15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }
    @Test
    public void qualityDegradesTwiceAsFast(){
        Item item = createAndUpdate("foo", 0, 17);
        assertEquals(15, item.quality);
    }

    @Test
    public void  qualityNeverNegative(){
        Item item = createAndUpdate("foo", 0, 0);
        assertEquals(0, item.quality);
    }

    @Test
    public void AgedBrieIncreaseInQuality(){
        Item item = createAndUpdate(GildedRose.AGED_BRIE, 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    public void sulfurasNeverHasToBeSoldOrDecreasesInQuality() {
        Item item = createAndUpdate(GildedRose.SULFURAS, 1, 42);
        assertEquals(1, item.sellIn); // sellIn doesn't change
        assertEquals(42, item.quality); // quality doesn't change
    }

    @Test
    public void backstagePassesIncreaseInQuality() {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    public void backstagePassesIncreaseBy2() {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 10, 25);
        assertEquals(27, item.quality);
    }

    @Test
    public void backstagePassesIncreaseBy3() {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 5, 25);
        assertEquals(28, item.quality);
    }

    @Test
    public void backstagePassesQualityDropTo0() {
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 0, 25);
        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieNeverExpires(){
        Item item = createAndUpdate(GildedRose.AGED_BRIE,0,42);
        assertEquals(-1, item.sellIn);
        assertEquals(44, item.quality);
    }

    @Test
    public void backstagePasseMaximumQuality(){
        Item item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 10, 48);
        assertEquals(GildedRose.MAXIMUM_QUALITY,item.quality);

        item = createAndUpdate(GildedRose.BACKSTAGE_PASSES, 10, 49);
        assertEquals(GildedRose.MAXIMUM_QUALITY,item.quality);
    }
}
