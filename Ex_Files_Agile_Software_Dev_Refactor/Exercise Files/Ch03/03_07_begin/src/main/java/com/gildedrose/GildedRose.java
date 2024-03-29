package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAXIMUM_QUALITY = 50;
    public static final int BACKSTAGE_PASS_THRESHOLD1 = 11;
    public static final int BACKSTAGE_PASS_THRESHOLD2 = 6;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (isNormalItem(item)) {
                handleNormalItem(item);
            } else if (isAgedBrie(item)) {
                handleAgedBrie(item);
            } else if (isBackstagePasses(item)) {
                handleBackstagePasses(item);
            } else if (isSulfuras(item)) {
                handleSulfuras(item);
            } else {
                if (isAgedBrie(item)
                        || isBackstagePasses(item)) {
                    if (item.quality < MAXIMUM_QUALITY) {
                        item.quality++;

                        if (isBackstagePasses(item)) {
                            if (item.sellIn < BACKSTAGE_PASS_THRESHOLD1) {
                                if (item.quality < MAXIMUM_QUALITY) {
                                    item.quality++;
                                }
                            }

                            if (item.sellIn < BACKSTAGE_PASS_THRESHOLD2) {
                                if (item.quality < MAXIMUM_QUALITY) {
                                    item.quality++;
                                }
                            }
                        }
                    }
                } else {
                    if (item.quality > 0) {
                        if (isSulfuras(item)) {
                        } else {
                            item.quality--;
                        }
                    }
                }

                if (isSulfuras(item)) {
                } else {
                    item.sellIn--;
                }

                if (item.sellIn < 0) {
                    if (isAgedBrie(item)) {
                        if (item.quality < MAXIMUM_QUALITY) {
                            item.quality++;
                        }
                    } else {
                        if (isBackstagePasses(item)) {
                            item.quality = 0;
                        } else {
                            if (item.quality > 0) {
                                if (isSulfuras(item)) {
                                } else {
                                    item.quality--;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void handleSulfuras(Item item) {
        // We always write the least amount of code to make the pin-down
        // tests go green. In this case, we didn't have to write any
        // code -- so this is the correct solution.
    }

    private void handleBackstagePasses(Item item) {
        item.sellIn--;
        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn < BACKSTAGE_PASS_THRESHOLD2) {
            item.quality = item.quality + 3;
        } else if (item.sellIn < BACKSTAGE_PASS_THRESHOLD1) {
            item.quality = item.quality + 2;
        } else {
            item.quality++;
        }
        if (item.quality > MAXIMUM_QUALITY) {
            item.quality = MAXIMUM_QUALITY;
        }
    }

    private void handleAgedBrie(Item item) {
        item.sellIn--;
        if (item.sellIn <= 0) {
            item.quality = item.quality + 2;
        } else {
            item.quality++;
        }
        if (item.quality > MAXIMUM_QUALITY) {
            item.quality = MAXIMUM_QUALITY;
        }
    }

    private void handleNormalItem(Item item) {
        item.sellIn--;
        if (item.sellIn <= 0) {
            item.quality = item.quality - 2;
        } else {
            item.quality--;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    private boolean isNormalItem(Item item) {
        return !(isBackstagePasses(item) || isSulfuras(item) || isAgedBrie(item));
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}