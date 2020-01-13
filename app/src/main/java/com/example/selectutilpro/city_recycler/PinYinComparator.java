package com.example.selectutilpro.city_recycler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * 比较器，比较拼音的首字母
 */
public class PinYinComparator implements Comparator<PinYinComparator.CityBean> {
    @Override
    public int compare(CityBean lhs, CityBean rhs) {
        return lhs.getSortLetter().compareTo(rhs.getSortLetter());
    }

    public static class CityBean {
        private String name;
        private String sortLetter;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSortLetter() {
            return sortLetter;
        }

        public void setSortLetter(String sortLetter) {
            this.sortLetter = sortLetter;
        }
    }


    public static String getPinYin(String input) {
        ArrayList tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            Iterator iterator = tokens.iterator();
            while (iterator.hasNext()) {
                HanziToPinyin.Token token = (HanziToPinyin.Token) iterator.next();
                if (2 == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toUpperCase();
    }

}
