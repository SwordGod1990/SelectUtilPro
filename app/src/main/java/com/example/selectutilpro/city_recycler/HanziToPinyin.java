package com.example.selectutilpro.city_recycler;

import android.text.TextUtils;
import android.util.Log;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sos on 2016/8/25.
 */
public class HanziToPinyin {
    private static final String TAG = "HanziToPinyin";
    private static final boolean DEBUG = false;
    public static final char[] UNIHANS = new char[]{'阿', '哎', '安', '肮', '凹', '八', '挀', '扳',
            '邦', '勹', '陂', '奔', '伻', '屄', '边', '灬', '憋', '汃',
            '冫', '癶', '峬', '嚓', '偲', '参', '仓', '撡', '冊', '嵾', '曽',
            '曾', '層', '叉', '芆', '辿', '伥', '抄', '车', '抻', '沈', '沉',
            '阷', '吃', '充', '抽', '出', '欻', '揣', '巛', '刅', '吹', '旾',
            '逴', '呲', '匆', '凑', '粗', '汆', '崔', '邨', '搓', '咑', '呆',
            '丹', '当', '刀', '嘚', '扥', '灯', '氐', '嗲', '甸', '刁', '爹',
            '丁', '丟', '东', '吺', '厾', '耑', '襨', '吨', '多', '妸', '诶',
            '奀', '鞥', '儿', '发', '帆', '匚', '飞', '分', '丰', '覅', '仏',
            '紑', '伕', '旮', '侅', '甘', '冈', '皋', '戈', '给', '根', '刯',
            '工', '勾', '估', '瓜', '乖', '关', '光', '归', '丨', '呙', '哈',
            '咍', '佄', '夯', '茠', '诃', '黒', '拫', '亨', '噷', '叿', '齁',
            '乯', '花', '怀', '犿', '巟', '灰', '昏', '吙', '丌', '加', '戋',
            '江', '艽', '阶', '巾', '坕', '冂', '丩', '凥', '姢', '噘', '军',
            '咔', '开', '刊', '忼', '尻', '匼', '肎', '劥', '空', '抠', '扝',
            '夸', '蒯', '宽', '匡', '亏', '坤', '扩', '垃', '来', '兰', '啷',
            '捞', '肋', '勒', '崚', '刕', '俩', '奁', '良', '撩', '列', '拎',
            '刢', '溜', '囖', '龙', '瞜', '噜', '娈', '畧', '抡', '罗', '呣',
            '妈', '埋', '嫚', '牤', '猫', '么', '呅', '门', '甿', '咪', '宀',
            '喵', '乜', '民', '名', '谬', '摸', '哞', '毪', '嗯', '拏', '腉',
            '囡', '囔', '孬', '疒', '娞', '恁', '能', '妮', '拈', '嬢', '鸟',
            '捏', '囜', '宁', '妞', '农', '羺', '奴', '奻', '疟', '黁', '郍',
            '喔', '讴', '妑', '拍', '眅', '乓', '抛', '呸', '喷', '匉', '丕',
            '囨', '剽', '氕', '姘', '乒', '钋', '剖', '仆', '七', '掐', '千',
            '呛', '悄', '癿', '亲', '狅', '芎', '丘', '区', '峑', '缺', '夋',
            '呥', '穣', '娆', '惹', '人', '扔', '日', '茸', '厹', '邚', '挼',
            '堧', '婑', '瞤', '捼', '仨', '毢', '三', '桒', '掻', '閪', '森',
            '僧', '杀', '筛', '山', '伤', '弰', '奢', '申', '莘', '敒', '升',
            '尸', '収', '书', '刷', '衰', '闩', '双', '谁', '吮', '说', '厶',
            '忪', '捜', '苏', '狻', '夊', '孙', '唆', '他', '囼', '坍', '汤',
            '夲', '忑', '熥', '剔', '天', '旫', '帖', '厅', '囲', '偷', '凸',
            '湍', '推', '吞', '乇', '穵', '歪', '弯', '尣', '危', '昷', '翁',
            '挝', '乌', '夕', '虲', '仚', '乡', '灱', '些', '心', '星', '凶',
            '休', '吁', '吅', '削', '坃', '丫', '恹', '央', '幺', '倻', '一',
            '囙', '应', '哟', '佣', '优', '扜', '囦', '曰', '晕', '筠', '筼',
            '帀', '災', '兂', '匨', '傮', '则', '贼', '怎', '増', '扎', '捚',
            '沾', '张', '长', '長', '佋', '蜇', '贞', '争', '之', '峙', '庢',
            '中', '州', '朱', '抓', '拽', '专', '妆', '隹', '宒', '卓', '乲',
            '宗', '邹', '租', '钻', '厜', '尊', '昨', '兙', '鿃', '鿄'};
    public static final byte[][] PINYINS = new byte[][]{{(byte) 65, (byte) 0,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 65, (byte) 73,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 65, (byte) 78,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 65, (byte) 78,
            (byte) 71, (byte) 0, (byte) 0, (byte) 0}, {(byte) 65, (byte) 79,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 65,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 65,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 65,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 65,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 66, (byte) 65,
            (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 69,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 69,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 69,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 66, (byte) 73,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 73,
            (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 66, (byte) 73,
            (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 66, (byte) 73,
            (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 73,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 73,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 66, (byte) 79,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 66, (byte) 85,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 65,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 65,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 65,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 65,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 67, (byte) 65,
            (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 69,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 69,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 69,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 90, (byte) 69,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 67, (byte) 69,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 65, (byte) 73, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 69, (byte) 78, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72,
            (byte) 69, (byte) 78, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 69, (byte) 78, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 69, (byte) 78, (byte) 71, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 79, (byte) 78, (byte) 71, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 79, (byte) 85, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 65, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 65, (byte) 73, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 65, (byte) 78, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 65, (byte) 78, (byte) 71}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 73, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 78, (byte) 0, (byte) 0}, {(byte) 67, (byte) 72,
            (byte) 85, (byte) 79, (byte) 0, (byte) 0}, {(byte) 67, (byte) 73,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 79,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 67, (byte) 79,
            (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 85,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 85,
            (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 67, (byte) 85,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 85,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 67, (byte) 85,
            (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 65,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 65,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 65,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 65,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 68, (byte) 65,
            (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 69,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 69,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 69,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 68, (byte) 73,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 73,
            (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 73,
            (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 68, (byte) 73,
            (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 68, (byte) 73,
            (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 73,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 68, (byte) 73,
            (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 79,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 68, (byte) 79,
            (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 85,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 85,
            (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 68, (byte) 85,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 85,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 68, (byte) 85,
            (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 69, (byte) 0,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 69, (byte) 73,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 69, (byte) 78,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 69, (byte) 78,
            (byte) 71, (byte) 0, (byte) 0, (byte) 0}, {(byte) 69, (byte) 82,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 70, (byte) 65,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 70, (byte) 65,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 70, (byte) 65,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 70, (byte) 69,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 70, (byte) 69,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 70, (byte) 69,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 70, (byte) 73,
            (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 70, (byte) 79,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 70, (byte) 79,
            (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 70, (byte) 85,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 65,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 65,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 65,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 65,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 71, (byte) 65,
            (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 69,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 69,
            (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 69,
            (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 69,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 71, (byte) 79,
            (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 71, (byte) 79,
            (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 85,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 85, (byte) 65, (byte) 73, (byte) 0, (byte) 0}, {(byte) 71, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 71, (byte) 85, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 71, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 71, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 72, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 72, (byte) 77, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 72, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 85, (byte) 65, (byte) 73, (byte) 0, (byte) 0}, {(byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 72, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 72, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 74, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 74, (byte) 73, (byte) 79, (byte) 78, (byte) 71, (byte) 0}, {(byte) 74, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 74, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 75, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 75, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 75, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 85, (byte) 65, (byte) 73, (byte) 0, (byte) 0}, {(byte) 75, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 75, (byte) 85, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 75, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 75, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 76, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 76, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 76, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 76, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 76, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 76, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 77, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 77, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 77, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 77, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 77, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 77, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 78, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 78, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 78, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 78, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 78, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 78, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 78, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 78, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 78, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 80, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 80, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 80, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 80, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 80, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 80, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 81, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 81, (byte) 73, (byte) 79, (byte) 78, (byte) 71, (byte) 0}, {(byte) 81, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 81, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 81, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 82, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 82, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 82, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 82, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 82, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 83, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 65, (byte) 73, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 83, (byte) 72, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 69, (byte) 78, (byte) 71, (byte) 0}, {(byte) 83, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 79, (byte) 85, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 73, (byte) 0}, {(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 0}, {(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 71}, {(byte) 83, (byte) 72, (byte) 85, (byte) 73, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 85, (byte) 78, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 85, (byte) 79, (byte) 0, (byte) 0}, {(byte) 83, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 83, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 83, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 84, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 84, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 84, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 84, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 84, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 84, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 84, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 84, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 87, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 87, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 87, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 87, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 87, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 87, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 87, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 87, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 87, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 88, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 88, (byte) 73, (byte) 79, (byte) 78, (byte) 71, (byte) 0}, {(byte) 88, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 88, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 88, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 89, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 89, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 89, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 89, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 74, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 89, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 90, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 65, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 65, (byte) 73, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 67, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 90, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0}, {(byte) 90, (byte) 72, (byte) 65, (byte) 79, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 69, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 69, (byte) 78, (byte) 71, (byte) 0}, {(byte) 90, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 83, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 79, (byte) 78, (byte) 71, (byte) 0}, {(byte) 90, (byte) 72, (byte) 79, (byte) 85, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 73, (byte) 0}, {(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 0}, {(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 71}, {(byte) 90, (byte) 72, (byte) 85, (byte) 73, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 85, (byte) 78, (byte) 0, (byte) 0}, {(byte) 90, (byte) 72, (byte) 85, (byte) 79, (byte) 0, (byte) 0}, {(byte) 90, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0}, {(byte) 90, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, {(byte) 90, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0}, {(byte) 90, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0}, new byte[6], {(byte) 83, (byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0}, new byte[6]};
    private static final String FIRST_PINYIN_UNIHAN = "阿";
    private static final String LAST_PINYIN_UNIHAN = "\u9fff";
    private static final Collator COLLATOR;
    private static HanziToPinyin sInstance;
    private final boolean mHasChinaCollator;

    static {
        COLLATOR = Collator.getInstance(Locale.CHINA);
    }

    protected HanziToPinyin(boolean hasChinaCollator) {
        this.mHasChinaCollator = hasChinaCollator;
    }

    public static HanziToPinyin getInstance() {
        Class var0 = HanziToPinyin.class;
        synchronized (HanziToPinyin.class) {
            if (sInstance != null) {
                return sInstance;
            } else {
                final Locale locale[] = Collator.getAvailableLocales();
                final Locale chinaAddition = new Locale("zh");
//                Locale newChina = new Locale("zh", "HANS", "CN");//这部分是我做的修复，解决HTC兼容性问题
                for (int i = 0; i < locale.length; i++) {
                    if (locale[i].equals(Locale.CHINA) || locale[i].equals(chinaAddition)) {
                        // Do self validation just once.
                        if (DEBUG) {
                            Log.e(TAG, "Self validation. Result: " + doSelfValidation());
                        }
                        sInstance = new HanziToPinyin(true);
                        return sInstance;
                    }
                }


//                Locale[] locale = Collator.getAvailableLocales();
//
//                for (int i = 0; i < locale.length; ++i) {
//                    if (locale[i].equals(Locale.CHINESE)) {
//                        sInstance = new HanziToPinyin(true);
//                        return sInstance;
//                    }
//                }

                Log.e("HanziToPinyin", "There is no Chinese collator, HanziToPinyin is disabled");
                sInstance = new HanziToPinyin(false);
                return sInstance;
            }
        }
    }

    private static boolean doSelfValidation() {
        char lastChar = UNIHANS[0];
        String lastString = Character.toString(lastChar);
        char[] var5 = UNIHANS;
        int var4 = UNIHANS.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            char c = var5[var3];
            if (lastChar != c) {
                String curString = Character.toString(c);
                int cmp = COLLATOR.compare(lastString, curString);
                if (cmp >= 0) {
                    Log.e("HanziToPinyin", "Internal error in Unihan table. The last string \"" + lastString + "\" is greater than current string \"" + curString + "\".");
                    return false;
                }

                lastString = curString;
            }
        }

        return true;
    }

    private Token getToken(char character) {
        Token token = new Token();
        String letter = Character.toString(character);
        token.source = letter;
        int offset = -1;
        if (character < 256) {
            token.type = 1;
            token.target = letter;
            return token;
        } else {
            int cmp = COLLATOR.compare(letter, "阿");
            if (cmp < 0) {
                token.type = 3;
                token.target = letter;
                return token;
            } else {
                if (cmp == 0) {
                    token.type = 2;
                    offset = 0;
                } else {
                    cmp = COLLATOR.compare(letter, "\u9fff");
                    if (cmp > 0) {
                        token.type = 3;
                        token.target = letter;
                        return token;
                    }

                    if (cmp == 0) {
                        token.type = 2;
                        offset = UNIHANS.length - 1;
                    }
                }

                token.type = 2;
                int j;
                if (offset < 0) {
                    int pinyin = 0;
                    j = UNIHANS.length - 1;

                    while (pinyin <= j) {
                        offset = (pinyin + j) / 2;
                        String unihan = Character.toString(UNIHANS[offset]);
                        cmp = COLLATOR.compare(letter, unihan);
                        if (cmp == 0) {
                            break;
                        }

                        if (cmp > 0) {
                            pinyin = offset + 1;
                        } else {
                            j = offset - 1;
                        }
                    }
                }

                if (cmp < 0) {
                    --offset;
                }

                StringBuilder var9 = new StringBuilder();

                for (j = 0; j < PINYINS[offset].length && PINYINS[offset][j] != 0; ++j) {
                    var9.append((char) PINYINS[offset][j]);
                }

                token.target = var9.toString();
                if (TextUtils.isEmpty(token.target)) {
                    token.type = 3;
                    token.target = token.source;
                }

                return token;
            }
        }
    }

    public ArrayList<Token> get(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
        if (this.mHasChinaCollator && !TextUtils.isEmpty(input)) {
            int inputLength = input.length();
            StringBuilder sb = new StringBuilder();
            int tokenType = 1;

            for (int i = 0; i < inputLength; ++i) {
                char character = input.charAt(i);
                if (character == 32) {
                    if (sb.length() > 0) {
                        this.addToken(sb, tokens, tokenType);
                    }
                } else if (character < 256) {
                    if (tokenType != 1 && sb.length() > 0) {
                        this.addToken(sb, tokens, tokenType);
                    }

                    tokenType = 1;
                    sb.append(character);
                } else {
                    Token t = this.getToken(character);
                    if (t.type == 2) {
                        if (sb.length() > 0) {
                            this.addToken(sb, tokens, tokenType);
                        }

                        tokens.add(t);
                        tokenType = 2;
                    } else {
                        if (tokenType != t.type && sb.length() > 0) {
                            this.addToken(sb, tokens, tokenType);
                        }

                        tokenType = t.type;
                        sb.append(character);
                    }
                }
            }

            if (sb.length() > 0) {
                this.addToken(sb, tokens, tokenType);
            }

            return tokens;
        } else {
            return tokens;
        }
    }

    private void addToken(StringBuilder sb, ArrayList<Token> tokens, int tokenType) {
        String str = sb.toString();
        tokens.add(new Token(tokenType, str, str));
        sb.setLength(0);
    }

    public static class Token {
        public static final String SEPARATOR = " ";
        public static final int LATIN = 1;
        public static final int PINYIN = 2;
        public static final int UNKNOWN = 3;
        public int type;
        public String source;
        public String target;

        public Token() {
        }

        public Token(int type, String source, String target) {
            this.type = type;
            this.source = source;
            this.target = target;
        }
    }
}
