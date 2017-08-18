/*
 * Copyright 2016 Hani Al Momani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kevalpatel2106.emoji_keyboard.Helper;

import android.content.Context;
import android.text.Spannable;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.kevalpatel2106.emoji_keyboard.R;

/**
 * @author Hani Al Momani (hani.momanii@gmail.com)
 */


public final class EmojiconHandler {
    private EmojiconHandler() {
    }

    private static final SparseIntArray sEmojisMap = new SparseIntArray(1029);
    private static final SparseIntArray sSoftbanksMap = new SparseIntArray(471);
    private static Map<String, Integer> sEmojisModifiedMap = new HashMap<>();
    private static Set<String> sInexistentEmojis = new HashSet<>();

    static {
        // People

        sEmojisMap.put(0x1f600, R.drawable.emoji_1f600);
        sEmojisMap.put(0x1f62c, R.drawable.emoji_1f62c);
        sEmojisMap.put(0x1f601, R.drawable.emoji_1f601);
        sEmojisMap.put(0x1f602, R.drawable.emoji_1f602);
        sEmojisMap.put(0x1f603, R.drawable.emoji_1f603);
        sEmojisMap.put(0x1f604, R.drawable.emoji_1f604);
        sEmojisMap.put(0x1f605, R.drawable.emoji_1f605);
        sEmojisMap.put(0x1f606, R.drawable.emoji_1f606);
        sEmojisMap.put(0x1f607, R.drawable.emoji_1f607);
        sEmojisMap.put(0x1f609, R.drawable.emoji_1f609);
        sEmojisMap.put(0x1f60a, R.drawable.emoji_1f60a);
        sEmojisMap.put(0x1f642, R.drawable.emoji_1f642);
        sEmojisMap.put(0x1f643, R.drawable.emoji_1f643);
        sEmojisMap.put(0x263a, R.drawable.emoji_263a);
        sEmojisMap.put(0x1f60b, R.drawable.emoji_1f60b);
        sEmojisMap.put(0x1f60c, R.drawable.emoji_1f60c);
        sEmojisMap.put(0x1f60d, R.drawable.emoji_1f60d);
        sEmojisMap.put(0x1f618, R.drawable.emoji_1f618);
        sEmojisMap.put(0x1f617, R.drawable.emoji_1f617);
        sEmojisMap.put(0x1f619, R.drawable.emoji_1f619);
        sEmojisMap.put(0x1f61a, R.drawable.emoji_1f61a);
        sEmojisMap.put(0x1f61c, R.drawable.emoji_1f61c);
        sEmojisMap.put(0x1f61d, R.drawable.emoji_1f61d);
        sEmojisMap.put(0x1f61b, R.drawable.emoji_1f61b);
        sEmojisMap.put(0x1f911, R.drawable.emoji_1f911);
        sEmojisMap.put(0x1f913, R.drawable.emoji_1f913);
        sEmojisMap.put(0x1f60e, R.drawable.emoji_1f60e);
        sEmojisMap.put(0x1f917, R.drawable.emoji_1f917);
        sEmojisMap.put(0x1f60f, R.drawable.emoji_1f60f);
        sEmojisMap.put(0x1f636, R.drawable.emoji_1f636);
        sEmojisMap.put(0x1f610, R.drawable.emoji_1f610);
        sEmojisMap.put(0x1f611, R.drawable.emoji_1f611);
        sEmojisMap.put(0x1f612, R.drawable.emoji_1f612);
        sEmojisMap.put(0x1f644, R.drawable.emoji_1f644);
        sEmojisMap.put(0x1f914, R.drawable.emoji_1f914);
        sEmojisMap.put(0x1f633, R.drawable.emoji_1f633);
        sEmojisMap.put(0x1f61e, R.drawable.emoji_1f61e);
        sEmojisMap.put(0x1f61f, R.drawable.emoji_1f61f);
        sEmojisMap.put(0x1f620, R.drawable.emoji_1f620);
        sEmojisMap.put(0x1f621, R.drawable.emoji_1f621);
        sEmojisMap.put(0x1f614, R.drawable.emoji_1f614);
        sEmojisMap.put(0x1f615, R.drawable.emoji_1f615);
        sEmojisMap.put(0x1f641, R.drawable.emoji_1f641);
        sEmojisMap.put(0x2639, R.drawable.emoji_2639);
        sEmojisMap.put(0x1f623, R.drawable.emoji_1f623);
        sEmojisMap.put(0x1f616, R.drawable.emoji_1f616);
        sEmojisMap.put(0x1f62b, R.drawable.emoji_1f62b);
        sEmojisMap.put(0x1f629, R.drawable.emoji_1f629);
        sEmojisMap.put(0x1f624, R.drawable.emoji_1f624);
        sEmojisMap.put(0x1f62e, R.drawable.emoji_1f62e);
        sEmojisMap.put(0x1f631, R.drawable.emoji_1f631);
        sEmojisMap.put(0x1f628, R.drawable.emoji_1f628);
        sEmojisMap.put(0x1f630, R.drawable.emoji_1f630);
        sEmojisMap.put(0x1f62f, R.drawable.emoji_1f62f);
        sEmojisMap.put(0x1f626, R.drawable.emoji_1f626);
        sEmojisMap.put(0x1f627, R.drawable.emoji_1f627);
        sEmojisMap.put(0x1f622, R.drawable.emoji_1f622);
        sEmojisMap.put(0x1f625, R.drawable.emoji_1f625);
        sEmojisMap.put(0x1f62a, R.drawable.emoji_1f62a);
        sEmojisMap.put(0x1f613, R.drawable.emoji_1f613);
        sEmojisMap.put(0x1f62d, R.drawable.emoji_1f62d);
        sEmojisMap.put(0x1f635, R.drawable.emoji_1f635);
        sEmojisMap.put(0x1f632, R.drawable.emoji_1f632);
        sEmojisMap.put(0x1f910, R.drawable.emoji_1f910);
        sEmojisMap.put(0x1f637, R.drawable.emoji_1f637);
        sEmojisMap.put(0x1f912, R.drawable.emoji_1f912);
        sEmojisMap.put(0x1f915, R.drawable.emoji_1f915);
        sEmojisMap.put(0x1f634, R.drawable.emoji_1f634);
        sEmojisMap.put(0x1f4a4, R.drawable.emoji_1f4a4);
        sEmojisMap.put(0x1f4a9, R.drawable.emoji_1f4a9);
        sEmojisMap.put(0x1f608, R.drawable.emoji_1f608);
        sEmojisMap.put(0x1f47f, R.drawable.emoji_1f47f);
        sEmojisMap.put(0x1f479, R.drawable.emoji_1f479);
        sEmojisMap.put(0x1f47a, R.drawable.emoji_1f47a);
        sEmojisMap.put(0x1f480, R.drawable.emoji_1f480);
        sEmojisMap.put(0x1f47b, R.drawable.emoji_1f47b);
        sEmojisMap.put(0x1f47d, R.drawable.emoji_1f47d);
        sEmojisMap.put(0x1f916, R.drawable.emoji_1f916);
        sEmojisMap.put(0x1f63a, R.drawable.emoji_1f63a);
        sEmojisMap.put(0x1f638, R.drawable.emoji_1f638);
        sEmojisMap.put(0x1f639, R.drawable.emoji_1f639);
        sEmojisMap.put(0x1f63b, R.drawable.emoji_1f63b);
        sEmojisMap.put(0x1f63c, R.drawable.emoji_1f63c);
        sEmojisMap.put(0x1f63d, R.drawable.emoji_1f63d);
        sEmojisMap.put(0x1f640, R.drawable.emoji_1f640);
        sEmojisMap.put(0x1f63f, R.drawable.emoji_1f63f);
        sEmojisMap.put(0x1f63e, R.drawable.emoji_1f63e);
        sEmojisMap.put(0x1f64c, R.drawable.emoji_1f64c_1f3fb);
        sEmojisMap.put(0x1f44f, R.drawable.emoji_1f44f_1f3fb);
        sEmojisMap.put(0x1f44b, R.drawable.emoji_1f44b_1f3fb);
        sEmojisMap.put(0x1f44d, R.drawable.emoji_1f44d_1f3fb);
        sEmojisMap.put(0x1f44e, R.drawable.emoji_1f44e_1f3fb);
        sEmojisMap.put(0x1f44a, R.drawable.emoji_1f44a_1f3fb);
        sEmojisMap.put(0x270a, R.drawable.emoji_270a_1f3fb);
        sEmojisMap.put(0x270c, R.drawable.emoji_270c_1f3fb);
        sEmojisMap.put(0x1f44c, R.drawable.emoji_1f44c_1f3fb);
        sEmojisMap.put(0x270b, R.drawable.emoji_270b_1f3fb);
        sEmojisMap.put(0x1f450, R.drawable.emoji_1f450_1f3fb);
        sEmojisMap.put(0x1f4aa, R.drawable.emoji_1f4aa_1f3fb);
        sEmojisMap.put(0x1f64f, R.drawable.emoji_1f64f_1f3fb);
        sEmojisMap.put(0x261d, R.drawable.emoji_261d_1f3fb);
        sEmojisMap.put(0x1f446, R.drawable.emoji_1f446_1f3fb);
        sEmojisMap.put(0x1f447, R.drawable.emoji_1f447_1f3fb);
        sEmojisMap.put(0x1f448, R.drawable.emoji_1f448_1f3fb);
        sEmojisMap.put(0x1f449, R.drawable.emoji_1f449_1f3fb);
        sEmojisMap.put(0x1f595, R.drawable.emoji_1f595_1f3fb);
        sEmojisMap.put(0x1f590, R.drawable.emoji_1f590_1f3fb);
        sEmojisMap.put(0x1f918, R.drawable.emoji_1f918_1f3fb);
        sEmojisMap.put(0x270d, R.drawable.emoji_270d_1f3fb);
        sEmojisMap.put(0x1f485, R.drawable.emoji_1f485_1f3fb);
        sEmojisMap.put(0x1f444, R.drawable.emoji_1f444);
        sEmojisMap.put(0x1f445, R.drawable.emoji_1f445);
        sEmojisMap.put(0x1f442, R.drawable.emoji_1f442_1f3fb);
        sEmojisMap.put(0x1f443, R.drawable.emoji_1f443_1f3fb);
        sEmojisMap.put(0x1f441, R.drawable.emoji_1f441);
        sEmojisMap.put(0x1f440, R.drawable.emoji_1f440);
        sEmojisMap.put(0x1f464, R.drawable.emoji_1f464);
        sEmojisMap.put(0x1f465, R.drawable.emoji_1f465);
        sEmojisMap.put(0x1f5e3, R.drawable.emoji_1f5e3);
        sEmojisMap.put(0x1f476, R.drawable.emoji_1f476_1f3fb);
        sEmojisMap.put(0x1f466, R.drawable.emoji_1f466_1f3fb);
        sEmojisMap.put(0x1f467, R.drawable.emoji_1f467_1f3fb);
        sEmojisMap.put(0x1f468, R.drawable.emoji_1f468_1f3fb);
        sEmojisMap.put(0x1f469, R.drawable.emoji_1f469_1f3fb);
        sEmojisMap.put(0x1f471, R.drawable.emoji_1f471_1f3fb);
        sEmojisMap.put(0x1f474, R.drawable.emoji_1f474_1f3fb);
        sEmojisMap.put(0x1f475, R.drawable.emoji_1f475_1f3fb);
        sEmojisMap.put(0x1f472, R.drawable.emoji_1f472_1f3fb);
        sEmojisMap.put(0x1f473, R.drawable.emoji_1f473_1f3fb);
        sEmojisMap.put(0x1f46e, R.drawable.emoji_1f46e_1f3fb);
        sEmojisMap.put(0x1f477, R.drawable.emoji_1f477_1f3fb);
        sEmojisMap.put(0x1f482, R.drawable.emoji_1f482_1f3fb);
        sEmojisMap.put(0x1f575, R.drawable.emoji_1f575);
        sEmojisMap.put(0x1f385, R.drawable.emoji_1f385_1f3fb);
        sEmojisMap.put(0x1f47c, R.drawable.emoji_1f47c_1f3fb);
        sEmojisMap.put(0x1f478, R.drawable.emoji_1f478_1f3fb);
        sEmojisMap.put(0x1f470, R.drawable.emoji_1f470_1f3fb);
        sEmojisMap.put(0x1f6b6, R.drawable.emoji_1f6b6_1f3fb);
        sEmojisMap.put(0x1f3c3, R.drawable.emoji_1f3c3_1f3fb);
        sEmojisMap.put(0x1f483, R.drawable.emoji_1f483_1f3fb);
        sEmojisMap.put(0x1f46f, R.drawable.emoji_1f46f);
        sEmojisMap.put(0x2728, R.drawable.emoji_2728);
        sEmojisMap.put(0x1f31f, R.drawable.emoji_1f31f);
        sEmojisMap.put(0x1f4ab, R.drawable.emoji_1f4ab);
        sEmojisMap.put(0x1f4a2, R.drawable.emoji_1f4a2);

        sEmojisMap.put(0x1f46b, R.drawable.emoji_1f46b);
        sEmojisMap.put(0x1f46c, R.drawable.emoji_1f46c);
        sEmojisMap.put(0x1f46d, R.drawable.emoji_1f46d);
        sEmojisMap.put(0x1f647, R.drawable.emoji_1f647_1f3fb);
        sEmojisMap.put(0x1f481, R.drawable.emoji_1f481_1f3fb);
        sEmojisMap.put(0x1f645, R.drawable.emoji_1f645_1f3fb);
        sEmojisMap.put(0x1f646, R.drawable.emoji_1f646_1f3fb);
        sEmojisMap.put(0x1f64b, R.drawable.emoji_1f64b_1f3fb);
        sEmojisMap.put(0x1f64e, R.drawable.emoji_1f64e_1f3fb);
        sEmojisMap.put(0x1f64d, R.drawable.emoji_1f64d_1f3fb);
        sEmojisMap.put(0x1f487, R.drawable.emoji_1f487_1f3fb);
        sEmojisMap.put(0x1f486, R.drawable.emoji_1f486_1f3fb);
        sEmojisMap.put(0x1f491, R.drawable.emoji_1f491);
        sEmojisMap.put(0x1f48f, R.drawable.emoji_1f48f);
        sEmojisMap.put(0x1f46a, R.drawable.emoji_1f46a);
        sEmojisMap.put(0x1f45a, R.drawable.emoji_1f45a);
        sEmojisMap.put(0x1f455, R.drawable.emoji_1f455);
        sEmojisMap.put(0x1f456, R.drawable.emoji_1f456);
        sEmojisMap.put(0x1f454, R.drawable.emoji_1f454);
        sEmojisMap.put(0x1f457, R.drawable.emoji_1f457);
        sEmojisMap.put(0x1f459, R.drawable.emoji_1f459);
        sEmojisMap.put(0x1f458, R.drawable.emoji_1f458);
        sEmojisMap.put(0x1f484, R.drawable.emoji_1f484);
        sEmojisMap.put(0x1f48b, R.drawable.emoji_1f48b);
        sEmojisMap.put(0x1f463, R.drawable.emoji_1f463);
        sEmojisMap.put(0x1f460, R.drawable.emoji_1f460);
        sEmojisMap.put(0x1f461, R.drawable.emoji_1f461);
        sEmojisMap.put(0x1f462, R.drawable.emoji_1f462);
        sEmojisMap.put(0x1f45e, R.drawable.emoji_1f45e);
        sEmojisMap.put(0x1f45f, R.drawable.emoji_1f45f);
        sEmojisMap.put(0x1f452, R.drawable.emoji_1f452);
        sEmojisMap.put(0x1f3a9, R.drawable.emoji_1f3a9);
        sEmojisMap.put(0x1f393, R.drawable.emoji_1f393);
        sEmojisMap.put(0x1f451, R.drawable.emoji_1f451);
        sEmojisMap.put(0x26d1, R.drawable.emoji_26d1);
        sSoftbanksMap.put(0xe43a, R.drawable.emoji_1f392);
        sEmojisMap.put(0x1f45d, R.drawable.emoji_1f45d);
        sEmojisMap.put(0x1f45b, R.drawable.emoji_1f45b);
        sEmojisMap.put(0x1f45c, R.drawable.emoji_1f45c);
        sEmojisMap.put(0x1f4bc, R.drawable.emoji_1f4bc);
        sEmojisMap.put(0x1f453, R.drawable.emoji_1f453);
        sEmojisMap.put(0x1f576, R.drawable.emoji_1f576);
        sEmojisMap.put(0x1f48d, R.drawable.emoji_1f48d);
        sEmojisMap.put(0x1f302, R.drawable.emoji_1f302);
        ////people


        // Nature
        sEmojisMap.put(0x1f436, R.drawable.emoji_1f436);
        sEmojisMap.put(0x1f431, R.drawable.emoji_1f431);
        sEmojisMap.put(0x1f42d, R.drawable.emoji_1f42d);
        sEmojisMap.put(0x1f439, R.drawable.emoji_1f439);
        sEmojisMap.put(0x1f430, R.drawable.emoji_1f430);
        sEmojisMap.put(0x1f43b, R.drawable.emoji_1f43b);
        sEmojisMap.put(0x1f43c, R.drawable.emoji_1f43c);
        sEmojisMap.put(0x1f428, R.drawable.emoji_1f428);
        sEmojisMap.put(0x1f42f, R.drawable.emoji_1f42f);
        sEmojisMap.put(0x1f981, R.drawable.emoji_1f981);
        sEmojisMap.put(0x1f42e, R.drawable.emoji_1f42e);
        sEmojisMap.put(0x1f437, R.drawable.emoji_1f437);
        sEmojisMap.put(0x1f43d, R.drawable.emoji_1f43d);
        sEmojisMap.put(0x1f438, R.drawable.emoji_1f438);
        sEmojisMap.put(0x1f419, R.drawable.emoji_1f419);
        sEmojisMap.put(0x1f435, R.drawable.emoji_1f435);
        sEmojisMap.put(0x1f648, R.drawable.emoji_1f648);
        sEmojisMap.put(0x1f649, R.drawable.emoji_1f649);
        sEmojisMap.put(0x1f64a, R.drawable.emoji_1f64a);
        sEmojisMap.put(0x1f412, R.drawable.emoji_1f412);
        sEmojisMap.put(0x1f414, R.drawable.emoji_1f414);
        sEmojisMap.put(0x1f427, R.drawable.emoji_1f427);
        sEmojisMap.put(0x1f426, R.drawable.emoji_1f426);
        sEmojisMap.put(0x1f424, R.drawable.emoji_1f424);
        sEmojisMap.put(0x1f423, R.drawable.emoji_1f423);
        sEmojisMap.put(0x1f425, R.drawable.emoji_1f425);
        sEmojisMap.put(0x1f43a, R.drawable.emoji_1f43a);
        sEmojisMap.put(0x1f417, R.drawable.emoji_1f417);
        sEmojisMap.put(0x1f434, R.drawable.emoji_1f434);
        sEmojisMap.put(0x1f984, R.drawable.emoji_1f984);
        sEmojisMap.put(0x1f41d, R.drawable.emoji_1f41d);
        sEmojisMap.put(0x1f41b, R.drawable.emoji_1f41b);
        sEmojisMap.put(0x1f40c, R.drawable.emoji_1f40c);
        sEmojisMap.put(0x1f41e, R.drawable.emoji_1f41e);
        sEmojisMap.put(0x1f41c, R.drawable.emoji_1f41c);
        sEmojisMap.put(0x1f577, R.drawable.emoji_1f577);
        sEmojisMap.put(0x1f982, R.drawable.emoji_1f982);
        sEmojisMap.put(0x1f980, R.drawable.emoji_1f980);
        sEmojisMap.put(0x1f40d, R.drawable.emoji_1f40d);
        sEmojisMap.put(0x1f422, R.drawable.emoji_1f422);
        sEmojisMap.put(0x1f420, R.drawable.emoji_1f420);
        sEmojisMap.put(0x1f41f, R.drawable.emoji_1f41f);
        sEmojisMap.put(0x1f421, R.drawable.emoji_1f421);
        sEmojisMap.put(0x1f42c, R.drawable.emoji_1f42c);
        sEmojisMap.put(0x1f433, R.drawable.emoji_1f433);
        sEmojisMap.put(0x1f40b, R.drawable.emoji_1f40b);
        sEmojisMap.put(0x1f40a, R.drawable.emoji_1f40a);
        sEmojisMap.put(0x1f406, R.drawable.emoji_1f406);
        sEmojisMap.put(0x1f405, R.drawable.emoji_1f405);
        sEmojisMap.put(0x1f403, R.drawable.emoji_1f403);
        sEmojisMap.put(0x1f402, R.drawable.emoji_1f402);
        sEmojisMap.put(0x1f404, R.drawable.emoji_1f404);
        sEmojisMap.put(0x1f42a, R.drawable.emoji_1f42a);
        sEmojisMap.put(0x1f42b, R.drawable.emoji_1f42b);
        sEmojisMap.put(0x1f418, R.drawable.emoji_1f418);
        sEmojisMap.put(0x1f410, R.drawable.emoji_1f410);
        sEmojisMap.put(0x1f40f, R.drawable.emoji_1f40f);
        sEmojisMap.put(0x1f411, R.drawable.emoji_1f411);
        sEmojisMap.put(0x1f40e, R.drawable.emoji_1f40e);
        sEmojisMap.put(0x1f416, R.drawable.emoji_1f416);
        sEmojisMap.put(0x1f400, R.drawable.emoji_1f400);
        sEmojisMap.put(0x1f401, R.drawable.emoji_1f401);
        sEmojisMap.put(0x1f413, R.drawable.emoji_1f413);
        sEmojisMap.put(0x1f983, R.drawable.emoji_1f983);
        sEmojisMap.put(0x1f54a, R.drawable.emoji_1f54a);
        sEmojisMap.put(0x1f415, R.drawable.emoji_1f415);
        sEmojisMap.put(0x1f429, R.drawable.emoji_1f429);
        sEmojisMap.put(0x1f408, R.drawable.emoji_1f408);
        sEmojisMap.put(0x1f407, R.drawable.emoji_1f407);
        sEmojisMap.put(0x1f43f, R.drawable.emoji_1f43f);
        sEmojisMap.put(0x1f43e, R.drawable.emoji_1f43e);
        sEmojisMap.put(0x1f409, R.drawable.emoji_1f409);
        sEmojisMap.put(0x1f432, R.drawable.emoji_1f432);
        sEmojisMap.put(0x1f335, R.drawable.emoji_1f335);
        sSoftbanksMap.put(0xe033, R.drawable.emoji_1f384);
        sEmojisMap.put(0x1f332, R.drawable.emoji_1f332);
        sEmojisMap.put(0x1f333, R.drawable.emoji_1f333);
        sEmojisMap.put(0x1f334, R.drawable.emoji_1f334);
        sSoftbanksMap.put(0xe110, R.drawable.emoji_1f331);
        sEmojisMap.put(0x1f33f, R.drawable.emoji_1f33f);
        sEmojisMap.put(0x1f340, R.drawable.emoji_1f340);
        sEmojisMap.put(0x1f38d, R.drawable.emoji_1f38d);
        sEmojisMap.put(0x1f38b, R.drawable.emoji_1f38b);
        sEmojisMap.put(0x1f343, R.drawable.emoji_1f343);
        sEmojisMap.put(0x1f342, R.drawable.emoji_1f342);
        sEmojisMap.put(0x1f341, R.drawable.emoji_1f341);
        sEmojisMap.put(0x1f33e, R.drawable.emoji_1f33e);
        sEmojisMap.put(0x1f33a, R.drawable.emoji_1f33a);
        sEmojisMap.put(0x1f33b, R.drawable.emoji_1f33b);
        sEmojisMap.put(0x1f339, R.drawable.emoji_1f339);
        sEmojisMap.put(0x1f337, R.drawable.emoji_1f337);
        sEmojisMap.put(0x1f33c, R.drawable.emoji_1f33c);
        sEmojisMap.put(0x1f338, R.drawable.emoji_1f338);
        sEmojisMap.put(0x1f490, R.drawable.emoji_1f490);
        sEmojisMap.put(0x1f344, R.drawable.emoji_1f344);
        sEmojisMap.put(0x1f330, R.drawable.emoji_1f330);
        sEmojisMap.put(0x1f383, R.drawable.emoji_1f383);
        sEmojisMap.put(0x1f41a, R.drawable.emoji_1f41a);
        sEmojisMap.put(0x1f578, R.drawable.emoji_1f578);
        sEmojisMap.put(0x1f30d, R.drawable.emoji_1f30d);
        sEmojisMap.put(0x1f30e, R.drawable.emoji_1f30e);
        sEmojisMap.put(0x1f30f, R.drawable.emoji_1f30f);
        sEmojisMap.put(0x1f315, R.drawable.emoji_1f315);
        sEmojisMap.put(0x1f316, R.drawable.emoji_1f316);
        sEmojisMap.put(0x1f317, R.drawable.emoji_1f317);
        sEmojisMap.put(0x1f318, R.drawable.emoji_1f318);
        sEmojisMap.put(0x1f311, R.drawable.emoji_1f311);
        sEmojisMap.put(0x1f312, R.drawable.emoji_1f312);
        sEmojisMap.put(0x1f313, R.drawable.emoji_1f313);
        sEmojisMap.put(0x1f314, R.drawable.emoji_1f314);
        sEmojisMap.put(0x1f31a, R.drawable.emoji_1f31a);
        sEmojisMap.put(0x1f31d, R.drawable.emoji_1f31d);
        sEmojisMap.put(0x1f31b, R.drawable.emoji_1f31b);
        sEmojisMap.put(0x1f31c, R.drawable.emoji_1f31c);
        sEmojisMap.put(0x1f31e, R.drawable.emoji_1f31e);
        sEmojisMap.put(0x1f319, R.drawable.emoji_1f319);
        sEmojisMap.put(0x2b50, R.drawable.emoji_2b50);
        sSoftbanksMap.put(0xe335, R.drawable.emoji_1f31f);
        sSoftbanksMap.put(0xe32e, R.drawable.emoji_2728);
        sEmojisMap.put(0x2604, R.drawable.emoji_2604);
        sEmojisMap.put(0x2600, R.drawable.emoji_2600);
        sEmojisMap.put(0x1f324, R.drawable.emoji_1f324);
        sEmojisMap.put(0x26c5, R.drawable.emoji_26c5);
        sEmojisMap.put(0x1f325, R.drawable.emoji_1f325);
        sEmojisMap.put(0x1f326, R.drawable.emoji_1f326);
        sEmojisMap.put(0x2601, R.drawable.emoji_2601);
        sEmojisMap.put(0x1f327, R.drawable.emoji_1f327);
        sEmojisMap.put(0x26c8, R.drawable.emoji_26c8);
        sEmojisMap.put(0x1f329, R.drawable.emoji_1f329);
        sEmojisMap.put(0x26a1, R.drawable.emoji_26a1);
        sSoftbanksMap.put(0xe11d, R.drawable.emoji_1f525);
        sEmojisMap.put(0x1f4a5, R.drawable.emoji_1f4a5);
        sEmojisMap.put(0x2744, R.drawable.emoji_2744);
        sEmojisMap.put(0x1f328, R.drawable.emoji_1f328);
        sEmojisMap.put(0x2603, R.drawable.emoji_2603);
        sEmojisMap.put(0x26c4, R.drawable.emoji_26c4);
        sEmojisMap.put(0x1f32c, R.drawable.emoji_1f32c);
        sEmojisMap.put(0x1f4a8, R.drawable.emoji_1f4a8);
        sEmojisMap.put(0x1f32a, R.drawable.emoji_1f32a);
        sEmojisMap.put(0x1f32b, R.drawable.emoji_1f32b);
        sEmojisMap.put(0x2602, R.drawable.emoji_2602);
        sEmojisMap.put(0x2614, R.drawable.emoji_2614);
        sEmojisMap.put(0x1f4a6, R.drawable.emoji_1f4a6);
        sEmojisMap.put(0x1f4a7, R.drawable.emoji_1f4a7);
        sEmojisMap.put(0x1f30a, R.drawable.emoji_1f30a);


///NATURE


        /////FOOD

        sEmojisMap.put(0x1f34f, R.drawable.emoji_1f34f);
        sEmojisMap.put(0x1f34e, R.drawable.emoji_1f34e);
        sEmojisMap.put(0x1f350, R.drawable.emoji_1f350);
        sEmojisMap.put(0x1f34a, R.drawable.emoji_1f34a);
        sEmojisMap.put(0x1f34b, R.drawable.emoji_1f34b);
        sEmojisMap.put(0x1f34c, R.drawable.emoji_1f34c);
        sEmojisMap.put(0x1f349, R.drawable.emoji_1f349);
        sEmojisMap.put(0x1f347, R.drawable.emoji_1f347);
        sEmojisMap.put(0x1f353, R.drawable.emoji_1f353);
        sEmojisMap.put(0x1f348, R.drawable.emoji_1f348);
        sEmojisMap.put(0x1f352, R.drawable.emoji_1f352);
        sEmojisMap.put(0x1f351, R.drawable.emoji_1f351);
        sEmojisMap.put(0x1f34d, R.drawable.emoji_1f34d);
        sEmojisMap.put(0x1f345, R.drawable.emoji_1f345);
        sEmojisMap.put(0x1f346, R.drawable.emoji_1f346);
        sEmojisMap.put(0x1f336, R.drawable.emoji_1f336);
        sEmojisMap.put(0x1f33d, R.drawable.emoji_1f33d);
        sEmojisMap.put(0x1f360, R.drawable.emoji_1f360);
        sEmojisMap.put(0x1f36f, R.drawable.emoji_1f36f);
        sEmojisMap.put(0x1f35e, R.drawable.emoji_1f35e);
        sEmojisMap.put(0x1f9c0, R.drawable.emoji_1f9c0);
        sEmojisMap.put(0x1f357, R.drawable.emoji_1f357);
        sEmojisMap.put(0x1f356, R.drawable.emoji_1f356);
        sEmojisMap.put(0x1f364, R.drawable.emoji_1f364);
        sEmojisMap.put(0x1f373, R.drawable.emoji_1f373);
        sEmojisMap.put(0x1f354, R.drawable.emoji_1f354);
        sEmojisMap.put(0x1f35f, R.drawable.emoji_1f35f);
        sEmojisMap.put(0x1f32d, R.drawable.emoji_1f32d);
        sEmojisMap.put(0x1f355, R.drawable.emoji_1f355);
        sEmojisMap.put(0x1f35d, R.drawable.emoji_1f35d);
        sEmojisMap.put(0x1f32e, R.drawable.emoji_1f32e);
        sEmojisMap.put(0x1f32f, R.drawable.emoji_1f32f);
        sEmojisMap.put(0x1f35c, R.drawable.emoji_1f35c);
        sEmojisMap.put(0x1f372, R.drawable.emoji_1f372);
        sEmojisMap.put(0x1f365, R.drawable.emoji_1f365);
        sEmojisMap.put(0x1f363, R.drawable.emoji_1f363);
        sEmojisMap.put(0x1f371, R.drawable.emoji_1f371);
        sEmojisMap.put(0x1f35b, R.drawable.emoji_1f35b);
        sEmojisMap.put(0x1f359, R.drawable.emoji_1f359);
        sEmojisMap.put(0x1f35a, R.drawable.emoji_1f35a);
        sEmojisMap.put(0x1f358, R.drawable.emoji_1f358);
        sEmojisMap.put(0x1f362, R.drawable.emoji_1f362);
        sEmojisMap.put(0x1f361, R.drawable.emoji_1f361);
        sEmojisMap.put(0x1f367, R.drawable.emoji_1f367);
        sEmojisMap.put(0x1f368, R.drawable.emoji_1f368);
        sEmojisMap.put(0x1f366, R.drawable.emoji_1f366);
        sEmojisMap.put(0x1f370, R.drawable.emoji_1f370);
        sEmojisMap.put(0x1f382, R.drawable.emoji_1f382);
        sEmojisMap.put(0x1f36e, R.drawable.emoji_1f36e);
        sEmojisMap.put(0x1f36c, R.drawable.emoji_1f36c);
        sEmojisMap.put(0x1f36d, R.drawable.emoji_1f36d);
        sEmojisMap.put(0x1f36b, R.drawable.emoji_1f36b);
        sEmojisMap.put(0x1f37f, R.drawable.emoji_1f37f);
        sEmojisMap.put(0x1f369, R.drawable.emoji_1f369);
        sEmojisMap.put(0x1f36a, R.drawable.emoji_1f36a);
        sEmojisMap.put(0x1f37a, R.drawable.emoji_1f37a);
        sEmojisMap.put(0x1f37b, R.drawable.emoji_1f37b);
        sEmojisMap.put(0x1f377, R.drawable.emoji_1f377);
        sEmojisMap.put(0x1f378, R.drawable.emoji_1f378);
        sEmojisMap.put(0x1f379, R.drawable.emoji_1f379);
        sEmojisMap.put(0x1f37e, R.drawable.emoji_1f37e);
        sEmojisMap.put(0x1f376, R.drawable.emoji_1f376);
        sEmojisMap.put(0x1f375, R.drawable.emoji_1f375);
        sEmojisMap.put(0x2615, R.drawable.emoji_2615);
        sEmojisMap.put(0x1f37c, R.drawable.emoji_1f37c);
        sEmojisMap.put(0x1f374, R.drawable.emoji_1f374);
        sEmojisMap.put(0x1f37d, R.drawable.emoji_1f37d);


        //////FOOOD


        //sport
        sEmojisMap.put(0x26bd, R.drawable.emoji_26bd);
        sEmojisMap.put(0x1f3c0, R.drawable.emoji_1f3c0);
        sEmojisMap.put(0x1f3c8, R.drawable.emoji_1f3c8);
        sEmojisMap.put(0x26be, R.drawable.emoji_26be);
        sEmojisMap.put(0x1f3be, R.drawable.emoji_1f3be);
        sEmojisMap.put(0x1f3d0, R.drawable.emoji_1f3d0);
        sEmojisMap.put(0x1f3c9, R.drawable.emoji_1f3c9);
        sEmojisMap.put(0x1f3b1, R.drawable.emoji_1f3b1);
        sEmojisMap.put(0x26f3, R.drawable.emoji_26f3);
        sEmojisMap.put(0x1f3cc, R.drawable.emoji_1f3cc);
        sEmojisMap.put(0x1f3d3, R.drawable.emoji_1f3d3);
        sEmojisMap.put(0x1f3f8, R.drawable.emoji_1f3f8);
        sEmojisMap.put(0x1f3d2, R.drawable.emoji_1f3d2);
        sEmojisMap.put(0x1f3d1, R.drawable.emoji_1f3d1);
        sEmojisMap.put(0x1f3cf, R.drawable.emoji_1f3cf);
        sEmojisMap.put(0x1f3bf, R.drawable.emoji_1f3bf);
        sEmojisMap.put(0x26f7, R.drawable.emoji_26f7);
        sEmojisMap.put(0x1f3c2, R.drawable.emoji_1f3c2);
        sEmojisMap.put(0x26f8, R.drawable.emoji_26f8);
        sEmojisMap.put(0x1f3f9, R.drawable.emoji_1f3f9);
        sEmojisMap.put(0x1f3a3, R.drawable.emoji_1f3a3);
        sEmojisMap.put(0x1f6a3, R.drawable.emoji_1f6a3_1f3fb);
        sEmojisMap.put(0x1f3ca, R.drawable.emoji_1f3ca_1f3fb);
        sEmojisMap.put(0x1f3c4, R.drawable.emoji_1f3c4_1f3fb);
        sEmojisMap.put(0x1f6c0, R.drawable.emoji_1f6c0_1f3fb);
        sEmojisMap.put(0x26f9, R.drawable.emoji_26f9_1f3fb);
        sEmojisMap.put(0x1f3cb, R.drawable.emoji_1f3cb_1f3fb);
        sEmojisMap.put(0x1f6b4, R.drawable.emoji_1f6b4_1f3fb);
        sEmojisMap.put(0x1f6b5, R.drawable.emoji_1f6b5_1f3fb);
        sEmojisMap.put(0x1f3c7, R.drawable.emoji_1f3c7_1f3fb);
        sEmojisMap.put(0x1f574, R.drawable.emoji_1f574);
        sEmojisMap.put(0x1f3c6, R.drawable.emoji_1f3c6);
        sEmojisMap.put(0x1f3bd, R.drawable.emoji_1f3bd);
        sEmojisMap.put(0x1f3c5, R.drawable.emoji_1f3c5);
        sEmojisMap.put(0x1f396, R.drawable.emoji_1f396);
        sEmojisMap.put(0x1f397, R.drawable.emoji_1f397);
        sEmojisMap.put(0x1f3f5, R.drawable.emoji_1f3f5);
        sSoftbanksMap.put(0xe125, R.drawable.emoji_1f3ab);
        sEmojisMap.put(0x1f39f, R.drawable.emoji_1f39f);
        sEmojisMap.put(0x1f3ad, R.drawable.emoji_1f3ad);
        sEmojisMap.put(0x1f3a8, R.drawable.emoji_1f3a8);
        sEmojisMap.put(0x1f3aa, R.drawable.emoji_1f3aa);
        sEmojisMap.put(0x1f3a4, R.drawable.emoji_1f3a4);
        sEmojisMap.put(0x1f3a7, R.drawable.emoji_1f3a7);
        sEmojisMap.put(0x1f3bc, R.drawable.emoji_1f3bc);
        sEmojisMap.put(0x1f3b9, R.drawable.emoji_1f3b9);
        sEmojisMap.put(0x1f3b7, R.drawable.emoji_1f3b7);
        sEmojisMap.put(0x1f3ba, R.drawable.emoji_1f3ba);
        sEmojisMap.put(0x1f3bb, R.drawable.emoji_1f3bb);
        sEmojisMap.put(0x1f3b8, R.drawable.emoji_1f3b8);
        sEmojisMap.put(0x1f3ac, R.drawable.emoji_1f3ac);
        sEmojisMap.put(0x1f3ae, R.drawable.emoji_1f3ae);
        sEmojisMap.put(0x1f47e, R.drawable.emoji_1f47e);
        sEmojisMap.put(0x1f3af, R.drawable.emoji_1f3af);
        sEmojisMap.put(0x1f3b2, R.drawable.emoji_1f3b2);
        sEmojisMap.put(0x1f3b0, R.drawable.emoji_1f3b0);
        sEmojisMap.put(0x1f3b3, R.drawable.emoji_1f3b3);


        //sport



///CARS

        sEmojisMap.put(0x1f697, R.drawable.emoji_1f697);
        sEmojisMap.put(0x1f695, R.drawable.emoji_1f695);
        sEmojisMap.put(0x1f699, R.drawable.emoji_1f699);
        sEmojisMap.put(0x1f68c, R.drawable.emoji_1f68c);
        sEmojisMap.put(0x1f68e, R.drawable.emoji_1f68e);
        sEmojisMap.put(0x1f3ce, R.drawable.emoji_1f3ce);
        sEmojisMap.put(0x1f693, R.drawable.emoji_1f693);
        sEmojisMap.put(0x1f691, R.drawable.emoji_1f691);
        sEmojisMap.put(0x1f692, R.drawable.emoji_1f692);
        sEmojisMap.put(0x1f690, R.drawable.emoji_1f690);
        sEmojisMap.put(0x1f69a, R.drawable.emoji_1f69a);
        sEmojisMap.put(0x1f69b, R.drawable.emoji_1f69b);
        sEmojisMap.put(0x1f69c, R.drawable.emoji_1f69c);
        sEmojisMap.put(0x1f3cd, R.drawable.emoji_1f3cd);
        sEmojisMap.put(0x1f6b2, R.drawable.emoji_1f6b2);
        sEmojisMap.put(0x1f6a8, R.drawable.emoji_1f6a8);
        sEmojisMap.put(0x1f694, R.drawable.emoji_1f694);
        sEmojisMap.put(0x1f68d, R.drawable.emoji_1f68d);
        sEmojisMap.put(0x1f698, R.drawable.emoji_1f698);
        sEmojisMap.put(0x1f696, R.drawable.emoji_1f696);
        sEmojisMap.put(0x1f6a1, R.drawable.emoji_1f6a1);
        sEmojisMap.put(0x1f6a0, R.drawable.emoji_1f6a0);
        sEmojisMap.put(0x1f69f, R.drawable.emoji_1f69f);
        sEmojisMap.put(0x1f68b, R.drawable.emoji_1f68b); // TODO (rockerhieu) review this emoji
        sEmojisMap.put(0x1f683, R.drawable.emoji_1f683);
        sEmojisMap.put(0x1f69d, R.drawable.emoji_1f69d);
        sEmojisMap.put(0x1f684, R.drawable.emoji_1f684);
        sEmojisMap.put(0x1f685, R.drawable.emoji_1f685);
        sEmojisMap.put(0x1f688, R.drawable.emoji_1f688);
        sEmojisMap.put(0x1f69e, R.drawable.emoji_1f69e);
        sEmojisMap.put(0x1f682, R.drawable.emoji_1f682);
        sEmojisMap.put(0x1f686, R.drawable.emoji_1f686);
        sEmojisMap.put(0x1f687, R.drawable.emoji_1f687);
        sEmojisMap.put(0x1f68a, R.drawable.emoji_1f68a);
        sEmojisMap.put(0x1f689, R.drawable.emoji_1f689);
        sEmojisMap.put(0x1f681, R.drawable.emoji_1f681);
        sEmojisMap.put(0x1f6e9, R.drawable.emoji_1f6e9);
        sEmojisMap.put(0x2708, R.drawable.emoji_2708);
        sEmojisMap.put(0x1f6eb, R.drawable.emoji_1f6eb);
        sEmojisMap.put(0x1f6ec, R.drawable.emoji_1f6ec);
        sEmojisMap.put(0x26f5, R.drawable.emoji_26f5);
        sEmojisMap.put(0x1f6e5, R.drawable.emoji_1f6e5);
        sEmojisMap.put(0x1f6a4, R.drawable.emoji_1f6a4);
        sEmojisMap.put(0x26f4, R.drawable.emoji_26f4);
        sEmojisMap.put(0x1f6f3, R.drawable.emoji_1f6f3);
        sEmojisMap.put(0x1f680, R.drawable.emoji_1f680);
        sEmojisMap.put(0x1f6f0, R.drawable.emoji_1f6f0);
        sEmojisMap.put(0x1f4ba, R.drawable.emoji_1f4ba);
        sEmojisMap.put(0x2693, R.drawable.emoji_2693);
        sEmojisMap.put(0x1f6a7, R.drawable.emoji_1f6a7);
        sEmojisMap.put(0x26fd, R.drawable.emoji_26fd);
        sEmojisMap.put(0x1f68f, R.drawable.emoji_1f68f);
        sEmojisMap.put(0x1f6a6, R.drawable.emoji_1f6a6);
        sEmojisMap.put(0x1f6a5, R.drawable.emoji_1f6a5);
        sEmojisMap.put(0x1f3c1, R.drawable.emoji_1f3c1);
        sEmojisMap.put(0x1f6a2, R.drawable.emoji_1f6a2);
        sEmojisMap.put(0x1f3a2, R.drawable.emoji_1f3a2);
        sEmojisMap.put(0x1f3a0, R.drawable.emoji_1f3a0);
        sEmojisMap.put(0x1f3d7, R.drawable.emoji_1f3d7);
        sEmojisMap.put(0x1f301, R.drawable.emoji_1f301);
        sEmojisMap.put(0x1f5fc, R.drawable.emoji_1f5fc);
        sEmojisMap.put(0x1f3ed, R.drawable.emoji_1f3ed);
        sEmojisMap.put(0x26f2, R.drawable.emoji_26f2);
        sSoftbanksMap.put(0xe446, R.drawable.emoji_1f391);
        sEmojisMap.put(0x26f0, R.drawable.emoji_26f0);
        sEmojisMap.put(0x1f3d4, R.drawable.emoji_1f3d4);
        sEmojisMap.put(0x1f5fb, R.drawable.emoji_1f5fb);
        sEmojisMap.put(0x1f30b, R.drawable.emoji_1f30b);
        sEmojisMap.put(0x1f5fe, R.drawable.emoji_1f5fe);
        sEmojisMap.put(0x1f3d5, R.drawable.emoji_1f3d5);
        sEmojisMap.put(0x26fa, R.drawable.emoji_26fa);
        sEmojisMap.put(0x1f3de, R.drawable.emoji_1f3de);
        sEmojisMap.put(0x1f6e3, R.drawable.emoji_1f6e3);
        sEmojisMap.put(0x1f6e4, R.drawable.emoji_1f6e4);
        sEmojisMap.put(0x1f305, R.drawable.emoji_1f305);
        sEmojisMap.put(0x1f304, R.drawable.emoji_1f304);
        sEmojisMap.put(0x1f3dc, R.drawable.emoji_1f3dc);
        sEmojisMap.put(0x1f3d6, R.drawable.emoji_1f3d6);
        sEmojisMap.put(0x1f3dd, R.drawable.emoji_1f3dd);
        sEmojisMap.put(0x1f307, R.drawable.emoji_1f307);
        sEmojisMap.put(0x1f306, R.drawable.emoji_1f306);
        sEmojisMap.put(0x1f3d9, R.drawable.emoji_1f3d9);
        sEmojisMap.put(0x1f320, R.drawable.emoji_1f303); // TODO (rockerhieu) review this emoji
        sEmojisMap.put(0x1f309, R.drawable.emoji_1f309);
        sEmojisMap.put(0x1f30c, R.drawable.emoji_1f30c);
        sEmojisMap.put(0x1f386, R.drawable.emoji_1f386);
        sEmojisMap.put(0x1f387, R.drawable.emoji_1f387);
        sSoftbanksMap.put(0xe44c, R.drawable.emoji_1f308);
        sEmojisMap.put(0x1f3d8, R.drawable.emoji_1f3d8);
        sEmojisMap.put(0x1f3f0, R.drawable.emoji_1f3f0);
        sEmojisMap.put(0x1f3ef, R.drawable.emoji_1f3ef);
        sEmojisMap.put(0x1f3df, R.drawable.emoji_1f3df);
        sEmojisMap.put(0x1f5fd, R.drawable.emoji_1f5fd);
        sEmojisMap.put(0x1f3e0, R.drawable.emoji_1f3e0);
        sEmojisMap.put(0x1f3e1, R.drawable.emoji_1f3e1);
        sEmojisMap.put(0x1f3da, R.drawable.emoji_1f3da);
        sEmojisMap.put(0x1f3e2, R.drawable.emoji_1f3e2);
        sEmojisMap.put(0x1f3ec, R.drawable.emoji_1f3ec);
        sEmojisMap.put(0x1f3e3, R.drawable.emoji_1f3e3);
        sEmojisMap.put(0x1f3e4, R.drawable.emoji_1f3e4);
        sEmojisMap.put(0x1f3e5, R.drawable.emoji_1f3e5);
        sEmojisMap.put(0x1f3e6, R.drawable.emoji_1f3e6);
        sEmojisMap.put(0x1f3e8, R.drawable.emoji_1f3e8);
        sEmojisMap.put(0x1f3ea, R.drawable.emoji_1f3ea);
        sEmojisMap.put(0x1f3eb, R.drawable.emoji_1f3eb);
        sEmojisMap.put(0x1f3e9, R.drawable.emoji_1f3e9);
        sEmojisMap.put(0x1f492, R.drawable.emoji_1f492);
        sEmojisMap.put(0x1f3db, R.drawable.emoji_1f3db);
        sEmojisMap.put(0x26ea, R.drawable.emoji_26ea);
        sEmojisMap.put(0x1f54c, R.drawable.emoji_1f54c);
        sEmojisMap.put(0x1f54b, R.drawable.emoji_1f54b);



        ///


/// ELECT


        sEmojisMap.put(0x231a, R.drawable.emoji_231a);
        sEmojisMap.put(0x1f4f1, R.drawable.emoji_1f4f1);
        sEmojisMap.put(0x1f4f2, R.drawable.emoji_1f4f2);
        sEmojisMap.put(0x1f4bb, R.drawable.emoji_1f4bb);
        sEmojisMap.put(0x2328, R.drawable.emoji_2328);
        sEmojisMap.put(0x1f5a5, R.drawable.emoji_1f5a5);
        sEmojisMap.put(0x1f5a8, R.drawable.emoji_1f5a8);
        sEmojisMap.put(0x1f5b1, R.drawable.emoji_1f5b1);
        sEmojisMap.put(0x1f5b2, R.drawable.emoji_1f5b2);
        sEmojisMap.put(0x1f579, R.drawable.emoji_1f579);
        sEmojisMap.put(0x1f5dc, R.drawable.emoji_1f5dc);
        sEmojisMap.put(0x1f4bd, R.drawable.emoji_1f4bd);
        sEmojisMap.put(0x1f4be, R.drawable.emoji_1f4be);
        sEmojisMap.put(0x1f4bf, R.drawable.emoji_1f4bf);
        sEmojisMap.put(0x1f4c0, R.drawable.emoji_1f4c0);
        sEmojisMap.put(0x1f4fc, R.drawable.emoji_1f4fc);
        sEmojisMap.put(0x1f4f7, R.drawable.emoji_1f4f7);
        sEmojisMap.put(0x1f4f8, R.drawable.emoji_1f4f8);
        sEmojisMap.put(0x1f4f9, R.drawable.emoji_1f4f9);
        sEmojisMap.put(0x1f3a5, R.drawable.emoji_1f3a5);
        sEmojisMap.put(0x1f4fd, R.drawable.emoji_1f4fd);
        sEmojisMap.put(0x1f39e, R.drawable.emoji_1f39e);
        sEmojisMap.put(0x1f4de, R.drawable.emoji_1f4de);
        sEmojisMap.put(0x260e, R.drawable.emoji_260e);
        sEmojisMap.put(0x1f4df, R.drawable.emoji_1f4df);
        sEmojisMap.put(0x1f4e0, R.drawable.emoji_1f4e0);
        sEmojisMap.put(0x1f4fa, R.drawable.emoji_1f4fa);
        sEmojisMap.put(0x1f4fb, R.drawable.emoji_1f4fb);
        sEmojisMap.put(0x1f399, R.drawable.emoji_1f399);
        sEmojisMap.put(0x1f39a, R.drawable.emoji_1f39a);
        sEmojisMap.put(0x1f39b, R.drawable.emoji_1f39b);
        sEmojisMap.put(0x23f1, R.drawable.emoji_23f1);
        sEmojisMap.put(0x23f2, R.drawable.emoji_23f2);
        sEmojisMap.put(0x23f0, R.drawable.emoji_23f0);
        sEmojisMap.put(0x1f570, R.drawable.emoji_1f570);
        sEmojisMap.put(0x23f3, R.drawable.emoji_23f3);
        sEmojisMap.put(0x231b, R.drawable.emoji_231b);
        sEmojisMap.put(0x1f4e1, R.drawable.emoji_1f4e1);
        sEmojisMap.put(0x1f50b, R.drawable.emoji_1f50b);
        sEmojisMap.put(0x1f50c, R.drawable.emoji_1f50c);
        sEmojisMap.put(0x1f4a1, R.drawable.emoji_1f4a1);
        sEmojisMap.put(0x1f526, R.drawable.emoji_1f526);
        sEmojisMap.put(0x1f56f, R.drawable.emoji_1f56f);
        sEmojisMap.put(0x1f5d1, R.drawable.emoji_1f5d1);
        sEmojisMap.put(0x1f6e2, R.drawable.emoji_1f6e2);
        sEmojisMap.put(0x1f4b8, R.drawable.emoji_1f4b8);
        sEmojisMap.put(0x1f4b5, R.drawable.emoji_1f4b5);
        sEmojisMap.put(0x1f4b4, R.drawable.emoji_1f4b4);
        sEmojisMap.put(0x1f4b7, R.drawable.emoji_1f4b7);
        sEmojisMap.put(0x1f4b6, R.drawable.emoji_1f4b6);
        sEmojisMap.put(0x1f4b0, R.drawable.emoji_1f4b0);
        sEmojisMap.put(0x1f4b3, R.drawable.emoji_1f4b3);
        sEmojisMap.put(0x1f48e, R.drawable.emoji_1f48e);
        sEmojisMap.put(0x2696, R.drawable.emoji_2696);
        sEmojisMap.put(0x1f527, R.drawable.emoji_1f527);
        sEmojisMap.put(0x1f528, R.drawable.emoji_1f528);
        sEmojisMap.put(0x2692, R.drawable.emoji_2692);
        sEmojisMap.put(0x1f6e0, R.drawable.emoji_1f6e0);
        sEmojisMap.put(0x26cf, R.drawable.emoji_26cf);
        sEmojisMap.put(0x1f529, R.drawable.emoji_1f529);
        sEmojisMap.put(0x2699, R.drawable.emoji_2699);
        sEmojisMap.put(0x26d3, R.drawable.emoji_26d3);
        sEmojisMap.put(0x1f52b, R.drawable.emoji_1f52b);
        sEmojisMap.put(0x1f4a3, R.drawable.emoji_1f4a3);
        sEmojisMap.put(0x1f52a, R.drawable.emoji_1f52a);
        sEmojisMap.put(0x1f5e1, R.drawable.emoji_1f5e1);
        sEmojisMap.put(0x2694, R.drawable.emoji_2694);
        sEmojisMap.put(0x1f6e1, R.drawable.emoji_1f6e1);
        sEmojisMap.put(0x1f6ac, R.drawable.emoji_1f6ac);
        sEmojisMap.put(0x2620, R.drawable.emoji_2620);
        sEmojisMap.put(0x26b0, R.drawable.emoji_26b0);
        sEmojisMap.put(0x26b1, R.drawable.emoji_26b1);
        sEmojisMap.put(0x1f3fa, R.drawable.emoji_1f3fa);
        sEmojisMap.put(0x1f52e, R.drawable.emoji_1f52e);
        sEmojisMap.put(0x1f4ff, R.drawable.emoji_1f4ff);
        sEmojisMap.put(0x1f488, R.drawable.emoji_1f488);
        sEmojisMap.put(0x2697, R.drawable.emoji_2697);
        sEmojisMap.put(0x1f52c, R.drawable.emoji_1f52c);
        sEmojisMap.put(0x1f52d, R.drawable.emoji_1f52d);
        sEmojisMap.put(0x1f573, R.drawable.emoji_1f573);
        sEmojisMap.put(0x1f48a, R.drawable.emoji_1f48a);
        sEmojisMap.put(0x1f489, R.drawable.emoji_1f489);
        sEmojisMap.put(0x1f321, R.drawable.emoji_1f321);
        sEmojisMap.put(0x1f3f7, R.drawable.emoji_1f3f7);
        sEmojisMap.put(0x1f516, R.drawable.emoji_1f516);
        sEmojisMap.put(0x1f6bd, R.drawable.emoji_1f6bd);
        sEmojisMap.put(0x1f6bf, R.drawable.emoji_1f6bf);
        sEmojisMap.put(0x1f6c1, R.drawable.emoji_1f6c1);
        sEmojisMap.put(0x1f511, R.drawable.emoji_1f511);
        sEmojisMap.put(0x1f5dd, R.drawable.emoji_1f5dd);
        sEmojisMap.put(0x1f6cb, R.drawable.emoji_1f6cb);
        sEmojisMap.put(0x1f6cc, R.drawable.emoji_1f6cc);
        sEmojisMap.put(0x1f6cf, R.drawable.emoji_1f6cf);
        sEmojisMap.put(0x1f6aa, R.drawable.emoji_1f6aa);
        sEmojisMap.put(0x1f6ce, R.drawable.emoji_1f6ce);
        sEmojisMap.put(0x1f5bc, R.drawable.emoji_1f5bc);
        sEmojisMap.put(0x1f5fa, R.drawable.emoji_1f5fa);
        sEmojisMap.put(0x26f1, R.drawable.emoji_26f1);
        sEmojisMap.put(0x1f5ff, R.drawable.emoji_1f5ff);
        sEmojisMap.put(0x1f6cd, R.drawable.emoji_1f6cd);
        sEmojisMap.put(0x1f388, R.drawable.emoji_1f388);
        sEmojisMap.put(0x1f38f, R.drawable.emoji_1f38f);
        sEmojisMap.put(0x1f380, R.drawable.emoji_1f380);
        sEmojisMap.put(0x1f381, R.drawable.emoji_1f381);
        sEmojisMap.put(0x1f38a, R.drawable.emoji_1f38a);
        sEmojisMap.put(0x1f389, R.drawable.emoji_1f389);
        sSoftbanksMap.put(0xe438, R.drawable.emoji_1f38e);
        sEmojisMap.put(0x1f390, R.drawable.emoji_1f390);
        sEmojisMap.put(0x1f38c, R.drawable.emoji_1f38c);
        sEmojisMap.put(0x1f3ee, R.drawable.emoji_1f3ee);
        sEmojisMap.put(0x2709, R.drawable.emoji_2709);
        sEmojisMap.put(0x1f4e9, R.drawable.emoji_1f4e9);
        sEmojisMap.put(0x1f4e8, R.drawable.emoji_1f4e8);
        sEmojisMap.put(0x1f4e7, R.drawable.emoji_1f4e7);
        sEmojisMap.put(0x1f48c, R.drawable.emoji_1f48c);
        sEmojisMap.put(0x1f4ee, R.drawable.emoji_1f4ee);
        sEmojisMap.put(0x1f4ea, R.drawable.emoji_1f4ea);
        sEmojisMap.put(0x1f4eb, R.drawable.emoji_1f4eb);
        sEmojisMap.put(0x1f4ec, R.drawable.emoji_1f4ec);
        sEmojisMap.put(0x1f4ed, R.drawable.emoji_1f4ed);
        sEmojisMap.put(0x1f4e6, R.drawable.emoji_1f4e6);
        sEmojisMap.put(0x1f4ef, R.drawable.emoji_1f4ef);
        sEmojisMap.put(0x1f4e5, R.drawable.emoji_1f4e5);
        sEmojisMap.put(0x1f4e4, R.drawable.emoji_1f4e4);
        sEmojisMap.put(0x1f4dc, R.drawable.emoji_1f4dc);
        sEmojisMap.put(0x1f4c3, R.drawable.emoji_1f4c3);
        sEmojisMap.put(0x1f4d1, R.drawable.emoji_1f4d1);
        sEmojisMap.put(0x1f4ca, R.drawable.emoji_1f4ca);
        sEmojisMap.put(0x1f4c8, R.drawable.emoji_1f4c8);
        sEmojisMap.put(0x1f4c9, R.drawable.emoji_1f4c9);
        sEmojisMap.put(0x1f4c4, R.drawable.emoji_1f4c4);
        sEmojisMap.put(0x1f4c5, R.drawable.emoji_1f4c5);
        sEmojisMap.put(0x1f4c6, R.drawable.emoji_1f4c6);
        sEmojisMap.put(0x1f5d3, R.drawable.emoji_1f5d3);
        sEmojisMap.put(0x1f4c7, R.drawable.emoji_1f4c7);
        sEmojisMap.put(0x1f5c3, R.drawable.emoji_1f5c3);
        sEmojisMap.put(0x1f5f3, R.drawable.emoji_1f5f3);
        sEmojisMap.put(0x1f5c4, R.drawable.emoji_1f5c4);
        sEmojisMap.put(0x1f4cb, R.drawable.emoji_1f4cb);
        sEmojisMap.put(0x1f5d2, R.drawable.emoji_1f5d2);
        sEmojisMap.put(0x1f4c1, R.drawable.emoji_1f4c1);
        sEmojisMap.put(0x1f4c2, R.drawable.emoji_1f4c2);
        sEmojisMap.put(0x1f5c2, R.drawable.emoji_1f5c2);
        sEmojisMap.put(0x1f5de, R.drawable.emoji_1f5de);
        sEmojisMap.put(0x1f4f0, R.drawable.emoji_1f4f0);
        sEmojisMap.put(0x1f4d3, R.drawable.emoji_1f4d3);
        sEmojisMap.put(0x1f4d5, R.drawable.emoji_1f4d5);
        sEmojisMap.put(0x1f4d7, R.drawable.emoji_1f4d7);
        sEmojisMap.put(0x1f4d8, R.drawable.emoji_1f4d8);
        sEmojisMap.put(0x1f4d9, R.drawable.emoji_1f4d9);
        sEmojisMap.put(0x1f4d4, R.drawable.emoji_1f4d4);
        sEmojisMap.put(0x1f4d2, R.drawable.emoji_1f4d2);
        sEmojisMap.put(0x1f4da, R.drawable.emoji_1f4da);
        sEmojisMap.put(0x1f4d6, R.drawable.emoji_1f4d6);
        sEmojisMap.put(0x1f517, R.drawable.emoji_1f517);
        sEmojisMap.put(0x1f4ce, R.drawable.emoji_1f4ce);
        sEmojisMap.put(0x1f587, R.drawable.emoji_1f587);
        sEmojisMap.put(0x2702, R.drawable.emoji_2702);
        sEmojisMap.put(0x1f4d0, R.drawable.emoji_1f4d0);
        sEmojisMap.put(0x1f4cf, R.drawable.emoji_1f4cf);
        sEmojisMap.put(0x1f4cc, R.drawable.emoji_1f4cc);
        sEmojisMap.put(0x1f4cd, R.drawable.emoji_1f4cd);
        sEmojisMap.put(0x1f3f3, R.drawable.emoji_1f3f3);
        sEmojisMap.put(0x1f3f4, R.drawable.emoji_1f3f4);
        sEmojisMap.put(0x1f510, R.drawable.emoji_1f510);
        sEmojisMap.put(0x1f512, R.drawable.emoji_1f512);
        sEmojisMap.put(0x1f513, R.drawable.emoji_1f513);
        sEmojisMap.put(0x1f50f, R.drawable.emoji_1f50f);
        sEmojisMap.put(0x1f58a, R.drawable.emoji_1f58a);
        sEmojisMap.put(0x1f58b, R.drawable.emoji_1f58b);
        sEmojisMap.put(0x2712, R.drawable.emoji_2712);
        sEmojisMap.put(0x1f4dd, R.drawable.emoji_1f4dd);
        sEmojisMap.put(0x270f, R.drawable.emoji_270f);
        sEmojisMap.put(0x1f58d, R.drawable.emoji_1f58d);
        sEmojisMap.put(0x1f58c, R.drawable.emoji_1f58c);
        sEmojisMap.put(0x1f50d, R.drawable.emoji_1f50d);
        sEmojisMap.put(0x1f50e, R.drawable.emoji_1f50e);

        ///ELECT



        /// SIGNES

        sEmojisMap.put(0x2764, R.drawable.emoji_2764);
        sEmojisMap.put(0x1f49b, R.drawable.emoji_1f49b);
        sEmojisMap.put(0x1f49a, R.drawable.emoji_1f49a);
        sEmojisMap.put(0x1f499, R.drawable.emoji_1f499);
        sEmojisMap.put(0x1f49c, R.drawable.emoji_1f49c);
        sEmojisMap.put(0x1f494, R.drawable.emoji_1f494);
        sEmojisMap.put(0x2763, R.drawable.emoji_2763);
        sEmojisMap.put(0x1f495, R.drawable.emoji_1f495);
        sEmojisMap.put(0x1f49e, R.drawable.emoji_1f49e);
        sEmojisMap.put(0x1f493, R.drawable.emoji_1f493);
        sEmojisMap.put(0x1f497, R.drawable.emoji_1f497);
        sEmojisMap.put(0x1f496, R.drawable.emoji_1f496);
        sEmojisMap.put(0x1f498, R.drawable.emoji_1f498);
        sEmojisMap.put(0x1f49d, R.drawable.emoji_1f49d);
        sEmojisMap.put(0x1f49f, R.drawable.emoji_1f49f);
        sEmojisMap.put(0x262e, R.drawable.emoji_262e);
        sEmojisMap.put(0x271d, R.drawable.emoji_271d);
        sEmojisMap.put(0x262a, R.drawable.emoji_262a);
        sEmojisMap.put(0x1f549, R.drawable.emoji_1f549);
        sEmojisMap.put(0x2638, R.drawable.emoji_2638);
        sEmojisMap.put(0x1f54e, R.drawable.emoji_1f54e);
        sEmojisMap.put(0x262f, R.drawable.emoji_262f);
        sEmojisMap.put(0x1f233, R.drawable.emoji_1f233);
        sEmojisMap.put(0x1f239, R.drawable.emoji_1f239);

        sSoftbanksMap.put(0xe532, R.drawable.emoji_1f170);
        sSoftbanksMap.put(0xe533, R.drawable.emoji_1f171);
        sSoftbanksMap.put(0xe534, R.drawable.emoji_1f18e);
        sSoftbanksMap.put(0xe535, R.drawable.emoji_1f17e);
        sEmojisMap.put(0x1f250, R.drawable.emoji_1f250);
        sEmojisMap.put(0x3299, R.drawable.emoji_3299);
        sEmojisMap.put(0x3297, R.drawable.emoji_3297);
        sEmojisMap.put(0x1f234, R.drawable.emoji_1f234);
        sEmojisMap.put(0x1f232, R.drawable.emoji_1f232);
        sEmojisMap.put(0x1f191, R.drawable.emoji_1f191);
        sEmojisMap.put(0x1f198, R.drawable.emoji_1f198);
        sEmojisMap.put(0x26d4, R.drawable.emoji_26d4);
        sEmojisMap.put(0x1f4db, R.drawable.emoji_1f4db);
        sEmojisMap.put(0x1f6ab, R.drawable.emoji_1f6ab);
        sEmojisMap.put(0x274c, R.drawable.emoji_274c);
        sEmojisMap.put(0x2b55, R.drawable.emoji_2b55);
        sSoftbanksMap.put(0xe334, R.drawable.emoji_1f4a2);
        sEmojisMap.put(0x1f51e, R.drawable.emoji_1f51e);
        sEmojisMap.put(0x1f4f5, R.drawable.emoji_1f4f5);
        sEmojisMap.put(0x1f6af, R.drawable.emoji_1f6af);
        sEmojisMap.put(0x1f6b1, R.drawable.emoji_1f6b1);
        sEmojisMap.put(0x1f6b3, R.drawable.emoji_1f6b3);
        sEmojisMap.put(0x1f6b7, R.drawable.emoji_1f6b7);
        sEmojisMap.put(0x203c, R.drawable.emoji_203c);
        sEmojisMap.put(0x2049, R.drawable.emoji_2049);
        sEmojisMap.put(0x2757, R.drawable.emoji_2757);
        sEmojisMap.put(0x2753, R.drawable.emoji_2753);
        sEmojisMap.put(0x2755, R.drawable.emoji_2755);
        sEmojisMap.put(0x2754, R.drawable.emoji_2754);
        sEmojisMap.put(0x1f4af, R.drawable.emoji_1f4af);
        sSoftbanksMap.put(0xe252, R.drawable.emoji_26a0);
        sEmojisMap.put(0x1f6b8, R.drawable.emoji_1f6b8);
        sEmojisMap.put(0x1f506, R.drawable.emoji_1f506);
        sEmojisMap.put(0x1f505, R.drawable.emoji_1f505);
        sEmojisMap.put(0x1f531, R.drawable.emoji_1f531);
        sEmojisMap.put(0x1f530, R.drawable.emoji_1f530);
        sEmojisMap.put(0x267b, R.drawable.emoji_267b);
        sEmojisMap.put(0x2733, R.drawable.emoji_2733);
        sEmojisMap.put(0x2747, R.drawable.emoji_2747);
        sEmojisMap.put(0x274e, R.drawable.emoji_274e);
        sEmojisMap.put(0x2705, R.drawable.emoji_2705);
        sEmojisMap.put(0x1f4b9, R.drawable.emoji_1f4b9);
        sEmojisMap.put(0x1f300, R.drawable.emoji_1f300);
        sEmojisMap.put(0x1f6be, R.drawable.emoji_1f6be);
        sEmojisMap.put(0x1f6b0, R.drawable.emoji_1f6b0);
        sEmojisMap.put(0x1f17f, R.drawable.emoji_1f17f);
        sEmojisMap.put(0x267f, R.drawable.emoji_267f);
        sEmojisMap.put(0x1f6ad, R.drawable.emoji_1f6ad);
        sEmojisMap.put(0x1f202, R.drawable.emoji_1f202);
        sEmojisMap.put(0x24c2, R.drawable.emoji_24c2);
        sEmojisMap.put(0x1f6c2, R.drawable.emoji_1f6c2);
        sEmojisMap.put(0x1f6c4, R.drawable.emoji_1f6c4);
        sEmojisMap.put(0x1f6c5, R.drawable.emoji_1f6c5);
        sEmojisMap.put(0x1f6c3, R.drawable.emoji_1f6c3);
        sEmojisMap.put(0x1f6b9, R.drawable.emoji_1f6b9);
        sEmojisMap.put(0x1f6ba, R.drawable.emoji_1f6ba);
        sEmojisMap.put(0x1f6bc, R.drawable.emoji_1f6bc);
        sEmojisMap.put(0x1f6bb, R.drawable.emoji_1f6bb);
        sEmojisMap.put(0x1f6ae, R.drawable.emoji_1f6ae);
        sSoftbanksMap.put(0xe225, R.drawable.emoji_0030);
        sSoftbanksMap.put(0xe21c, R.drawable.emoji_0031);
        sSoftbanksMap.put(0xe21d, R.drawable.emoji_0032);
        sSoftbanksMap.put(0xe21e, R.drawable.emoji_0033);
        sSoftbanksMap.put(0xe21f, R.drawable.emoji_0034);
        sSoftbanksMap.put(0xe220, R.drawable.emoji_0035);
        sSoftbanksMap.put(0xe221, R.drawable.emoji_0036);
        sSoftbanksMap.put(0xe222, R.drawable.emoji_0037);
        sSoftbanksMap.put(0xe223, R.drawable.emoji_0038);
        sSoftbanksMap.put(0xe224, R.drawable.emoji_0039);
        sEmojisMap.put(0x1f51f, R.drawable.emoji_1f51f);
        sEmojisMap.put(0x1f522, R.drawable.emoji_1f522);
        sEmojisMap.put(0x1f523, R.drawable.emoji_1f523);
        sEmojisMap.put(0x2b06, R.drawable.emoji_2b06);
        sEmojisMap.put(0x2b07, R.drawable.emoji_2b07);
        sEmojisMap.put(0x2b05, R.drawable.emoji_2b05);
        sEmojisMap.put(0x27a1, R.drawable.emoji_27a1);
        sEmojisMap.put(0x1f520, R.drawable.emoji_1f520);
        sEmojisMap.put(0x1f521, R.drawable.emoji_1f521);
        sEmojisMap.put(0x1f524, R.drawable.emoji_1f524);
        sEmojisMap.put(0x2197, R.drawable.emoji_2197);
        sEmojisMap.put(0x2196, R.drawable.emoji_2196);
        sEmojisMap.put(0x2198, R.drawable.emoji_2198);
        sEmojisMap.put(0x2199, R.drawable.emoji_2199);
        sEmojisMap.put(0x2194, R.drawable.emoji_2194);
        sEmojisMap.put(0x2195, R.drawable.emoji_2195);
        sEmojisMap.put(0x1f504, R.drawable.emoji_1f504);
        sEmojisMap.put(0x25c0, R.drawable.emoji_25c0);
        sEmojisMap.put(0x25b6, R.drawable.emoji_25b6);
        sEmojisMap.put(0x1f53c, R.drawable.emoji_1f53c);
        sEmojisMap.put(0x1f53d, R.drawable.emoji_1f53d);
        sEmojisMap.put(0x21a9, R.drawable.emoji_21a9);
        sEmojisMap.put(0x21aa, R.drawable.emoji_21aa);
        sEmojisMap.put(0x2139, R.drawable.emoji_2139);
        sEmojisMap.put(0x23ea, R.drawable.emoji_23ea);
        sEmojisMap.put(0x23e9, R.drawable.emoji_23e9);
        sEmojisMap.put(0x23ed, R.drawable.emoji_23ed);
        sEmojisMap.put(0x23ef, R.drawable.emoji_23ef);
        sEmojisMap.put(0x23ee, R.drawable.emoji_23ee);
        sEmojisMap.put(0x23f8, R.drawable.emoji_23f8);
        sEmojisMap.put(0x23f9, R.drawable.emoji_23f9);
        sEmojisMap.put(0x23fa, R.drawable.emoji_23fa);
        sEmojisMap.put(0x23eb, R.drawable.emoji_23eb);
        sEmojisMap.put(0x23ec, R.drawable.emoji_23ec);
        sEmojisMap.put(0x2935, R.drawable.emoji_2935);
        sEmojisMap.put(0x2934, R.drawable.emoji_2934);
        sEmojisMap.put(0x1f197, R.drawable.emoji_1f197);
        sEmojisMap.put(0x1f500, R.drawable.emoji_1f500);
        sEmojisMap.put(0x1f501, R.drawable.emoji_1f501);
        sEmojisMap.put(0x1f502, R.drawable.emoji_1f502);
        sEmojisMap.put(0x1f195, R.drawable.emoji_1f195);
        sEmojisMap.put(0x1f199, R.drawable.emoji_1f199);
        sEmojisMap.put(0x1f192, R.drawable.emoji_1f192);
        sEmojisMap.put(0x1f193, R.drawable.emoji_1f193);
        sEmojisMap.put(0x1f196, R.drawable.emoji_1f196);
        sEmojisMap.put(0x1f4f6, R.drawable.emoji_1f4f6);
        sEmojisMap.put(0x1f3a6, R.drawable.emoji_1f3a6);
        sEmojisMap.put(0x1f201, R.drawable.emoji_1f201);
        sEmojisMap.put(0x1f4b2, R.drawable.emoji_1f4b2);
        sEmojisMap.put(0x1f4b1, R.drawable.emoji_1f4b1);
        sEmojisMap.put(0x00a9, R.drawable.emoji_00a9);
        sEmojisMap.put(0x00ae, R.drawable.emoji_00ae);
        sEmojisMap.put(0x2122, R.drawable.emoji_2122);
        sEmojisMap.put(0x1f51d, R.drawable.emoji_1f51d);
        sEmojisMap.put(0x1f51a, R.drawable.emoji_1f51a);
        sEmojisMap.put(0x1f519, R.drawable.emoji_1f519);
        sEmojisMap.put(0x1f51b, R.drawable.emoji_1f51b);
        sEmojisMap.put(0x1f51c, R.drawable.emoji_1f51c);
        sEmojisMap.put(0x1f503, R.drawable.emoji_1f503);
        sEmojisMap.put(0x2716, R.drawable.emoji_2716);
        sEmojisMap.put(0x2795, R.drawable.emoji_2795);
        sEmojisMap.put(0x2796, R.drawable.emoji_2796);
        sEmojisMap.put(0x2797, R.drawable.emoji_2797);
        sEmojisMap.put(0x2714, R.drawable.emoji_2714);
        sEmojisMap.put(0x2611, R.drawable.emoji_2611);
        sEmojisMap.put(0x1f518, R.drawable.emoji_1f518);
        sEmojisMap.put(0x27b0, R.drawable.emoji_27b0);
        sEmojisMap.put(0x3030, R.drawable.emoji_3030);
        sEmojisMap.put(0x1f4ae, R.drawable.emoji_1f4ae);
        sEmojisMap.put(0x25fc, R.drawable.emoji_25fc);
        sEmojisMap.put(0x25fb, R.drawable.emoji_25fb);
        sEmojisMap.put(0x25fe, R.drawable.emoji_25fe);
        sEmojisMap.put(0x25fd, R.drawable.emoji_25fd);
        sEmojisMap.put(0x25aa, R.drawable.emoji_25aa);
        sEmojisMap.put(0x25ab, R.drawable.emoji_25ab);
        sEmojisMap.put(0x1f53a, R.drawable.emoji_1f53a);
        sEmojisMap.put(0x1f532, R.drawable.emoji_1f532);
        sEmojisMap.put(0x1f533, R.drawable.emoji_1f533);
        sEmojisMap.put(0x26ab, R.drawable.emoji_26ab);
        sEmojisMap.put(0x26aa, R.drawable.emoji_26aa);
        sEmojisMap.put(0x1f534, R.drawable.emoji_1f534);
        sEmojisMap.put(0x1f535, R.drawable.emoji_1f535);
        sEmojisMap.put(0x1f53b, R.drawable.emoji_1f53b);
        sEmojisMap.put(0x2b1c, R.drawable.emoji_2b1c);
        sEmojisMap.put(0x2b1b, R.drawable.emoji_2b1b);
        sEmojisMap.put(0x1f536, R.drawable.emoji_1f536);
        sEmojisMap.put(0x1f537, R.drawable.emoji_1f537);
        sEmojisMap.put(0x1f538, R.drawable.emoji_1f538);
        sEmojisMap.put(0x1f539, R.drawable.emoji_1f539);
        sEmojisMap.put(0x1f50a, R.drawable.emoji_1f50a);
        sEmojisMap.put(0x1f509, R.drawable.emoji_1f509);
        sEmojisMap.put(0x1f508, R.drawable.emoji_1f508); // TODO (rockerhieu): review this emoji
        sEmojisMap.put(0x1f507, R.drawable.emoji_1f507);
        sEmojisMap.put(0x1f514, R.drawable.emoji_1f514);
        sEmojisMap.put(0x1f515, R.drawable.emoji_1f515);
        sEmojisMap.put(0x1f4e2, R.drawable.emoji_1f4e2);
        sEmojisMap.put(0x1f4e3, R.drawable.emoji_1f4e3);
        sEmojisMap.put(0x1f0cf, R.drawable.emoji_1f0cf);
        sEmojisMap.put(0x1f004, R.drawable.emoji_1f004);
        sEmojisMap.put(0x2660, R.drawable.emoji_2660);
        sEmojisMap.put(0x2665, R.drawable.emoji_2665);
        sEmojisMap.put(0x2663, R.drawable.emoji_2663);
        sEmojisMap.put(0x2666, R.drawable.emoji_2666);
        sEmojisMap.put(0x1f3b4, R.drawable.emoji_1f3b4);
        sEmojisMap.put(0x1f4ac, R.drawable.emoji_1f4ac);
        sEmojisMap.put(0x1f5ef, R.drawable.emoji_1f5ef);
        sEmojisMap.put(0x1f4ad, R.drawable.emoji_1f4ad);
        sEmojisMap.put(0x1f55b, R.drawable.emoji_1f55b);
        sEmojisMap.put(0x1f567, R.drawable.emoji_1f567);
        sEmojisMap.put(0x1f550, R.drawable.emoji_1f550);
        sEmojisMap.put(0x1f55c, R.drawable.emoji_1f55c);
        sEmojisMap.put(0x1f551, R.drawable.emoji_1f551);
        sEmojisMap.put(0x1f55d, R.drawable.emoji_1f55d);
        sEmojisMap.put(0x1f552, R.drawable.emoji_1f552);
        sEmojisMap.put(0x1f55e, R.drawable.emoji_1f55e);
        sEmojisMap.put(0x1f553, R.drawable.emoji_1f553);
        sEmojisMap.put(0x1f55f, R.drawable.emoji_1f55f);
        sEmojisMap.put(0x1f554, R.drawable.emoji_1f554);
        sEmojisMap.put(0x1f560, R.drawable.emoji_1f560);
        sEmojisMap.put(0x1f555, R.drawable.emoji_1f555);
        sEmojisMap.put(0x1f556, R.drawable.emoji_1f556);
        sEmojisMap.put(0x1f557, R.drawable.emoji_1f557);
        sEmojisMap.put(0x1f558, R.drawable.emoji_1f558);
        sEmojisMap.put(0x1f559, R.drawable.emoji_1f559);
        sEmojisMap.put(0x1f55a, R.drawable.emoji_1f55a);
        sEmojisMap.put(0x1f561, R.drawable.emoji_1f561);
        sEmojisMap.put(0x1f562, R.drawable.emoji_1f562);
        sEmojisMap.put(0x1f563, R.drawable.emoji_1f563);
        sEmojisMap.put(0x1f564, R.drawable.emoji_1f564);
        sEmojisMap.put(0x1f565, R.drawable.emoji_1f565);
        sEmojisMap.put(0x1f566, R.drawable.emoji_1f566);


    }

    private static boolean isSoftBankEmoji(char c) {
        return ((c >> 12) == 0xe);
    }

    private static int getEmojiResource(Context context, int codePoint) {
        return sEmojisMap.get(codePoint);
    }

    private static int getSoftbankEmojiResource(char c) {
        return sSoftbanksMap.get(c);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     */
    public static void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize) {
        addEmojis(context, text, emojiSize, emojiAlignment, textSize, 0, -1, false);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param index
     * @param length
     */
    public static void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize, int index, int length) {
        addEmojis(context, text, emojiSize, emojiAlignment, textSize, index, length, false);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param useSystemDefault
     */
    public static void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment,
                                 int textSize, boolean useSystemDefault) {
        addEmojis(context, text, emojiSize, emojiAlignment, textSize, 0, -1, useSystemDefault);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param index
     * @param length
     * @param useSystemDefault
     */
    public static void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize, int index, int length, boolean useSystemDefault) {

        if (useSystemDefault) {
            return;
        }

        int textLength = text.length();
        int textLengthToProcessMax = textLength - index;
        int textLengthToProcess = length < 0 || length >= textLengthToProcessMax ? textLength : (length + index);

        // remove spans throughout all text
        EmojiconSpan[] oldSpans = text.getSpans(0, textLength, EmojiconSpan.class);
        for (int i = 0; i < oldSpans.length; i++) {
            text.removeSpan(oldSpans[i]);
        }

        int skip;
        for (int i = index; i < textLengthToProcess; i += skip) {
            skip = 0;
            int icon = 0;
            char c = text.charAt(i);
            if (isSoftBankEmoji(c)) {
                icon = getSoftbankEmojiResource(c);
                skip = icon == 0 ? 0 : 1;
            }

            if (icon == 0) {
                int unicode = Character.codePointAt(text, i);
                skip = Character.charCount(unicode);

                if (unicode > 0xff) {
                    icon = getEmojiResource(context, unicode);
                }

                if (i + skip < textLengthToProcess) {
                    int followUnicode = Character.codePointAt(text, i + skip);
                    //Non-spacing mark (Combining mark)
                    if (followUnicode == 0xfe0f) {
                        int followSkip = Character.charCount(followUnicode);
                        if (i + skip + followSkip < textLengthToProcess) {

                            int nextFollowUnicode = Character.codePointAt(text, i + skip + followSkip);
                            if (nextFollowUnicode == 0x20e3) {
                                int nextFollowSkip = Character.charCount(nextFollowUnicode);
                                int tempIcon = getKeyCapEmoji(unicode);

                                if (tempIcon == 0) {
                                    followSkip = 0;
                                    nextFollowSkip = 0;
                                } else {
                                    icon = tempIcon;
                                }
                                skip += (followSkip + nextFollowSkip);
                            }
                        }
                    } else if (followUnicode == 0x20e3) {
                        //some older versions of iOS don't use a combining character, instead it just goes straight to the second part
                        int followSkip = Character.charCount(followUnicode);

                        int tempIcon = getKeyCapEmoji(unicode);
                        if (tempIcon == 0) {
                            followSkip = 0;
                        } else {
                            icon = tempIcon;
                        }
                        skip += followSkip;

                    } else {
                        //handle other emoji modifiers
                        int followSkip = Character.charCount(followUnicode);

                        //TODO seems like we could do this for every emoji type rather than having that giant static map, maybe this is too slow?
                        String hexUnicode = Integer.toHexString(unicode);
                        String hexFollowUnicode = Integer.toHexString(followUnicode);

                        String resourceName = "emoji_" + hexUnicode + "_" + hexFollowUnicode;

                        int resourceId = 0;
                        if (sEmojisModifiedMap.containsKey(resourceName)) {
                            resourceId = sEmojisModifiedMap.get(resourceName);
                        } else if (!sInexistentEmojis.contains(resourceName)){
                            resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getApplicationContext().getPackageName());
                            if (resourceId != 0) {
                                sEmojisModifiedMap.put(resourceName, resourceId);
                            } else {
                              sInexistentEmojis.add(resourceName);
                            }
                        }

                        if (resourceId == 0) {
                            followSkip = 0;
                        } else {
                            icon = resourceId;
                        }
                        skip += followSkip;
                    }
                }
            }

            if (icon > 0) {
                text.setSpan(new EmojiconSpan(context, icon, emojiSize, emojiAlignment, textSize), i, i + skip, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private static int getKeyCapEmoji(int unicode) {
        int icon = 0;
        switch (unicode) {
            case 0x0023:
                icon = R.drawable.emoji_0023;
                break;
            case 0x002a:
                icon = R.drawable.emoji_002a_20e3;
                break;
            case 0x0030:
                icon = R.drawable.emoji_0030;
                break;
            case 0x0031:
                icon = R.drawable.emoji_0031;
                break;
            case 0x0032:
                icon = R.drawable.emoji_0032;
                break;
            case 0x0033:
                icon = R.drawable.emoji_0033;
                break;
            case 0x0034:
                icon = R.drawable.emoji_0034;
                break;
            case 0x0035:
                icon = R.drawable.emoji_0035;
                break;
            case 0x0036:
                icon = R.drawable.emoji_0036;
                break;
            case 0x0037:
                icon = R.drawable.emoji_0037;
                break;
            case 0x0038:
                icon = R.drawable.emoji_0038;
                break;
            case 0x0039:
                icon = R.drawable.emoji_0039;
                break;
            default:
                break;
        }
        return icon;
    }

}
