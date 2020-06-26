package com.company.view;

import com.company.model.Quest;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.FileNotFoundException;
import java.util.List;

public class QuestsView {
    public static void questManagmentMenu() {
        System.out.println("(1) View quests table\n" +
                "(2) Create quest\n" +
                "(3) Update quest data\n" +
                "(4) Delete quest\n" +
                "(5) View my quests\n" +
                "(0) Quit");
    }

    public static void updateQuestModes() {
        System.out.println("What do you want to edit? \n");
        System.out.println("(1) Title\n" +
                "(2) Description\n" +
                "(3) Amount of coins\n" +
                "(4) Id of mentor who created quest\n" +
                "(0) Back to the Quest Managment Menu");
    }

    public static void allQuestsByList(List<Quest> newList) throws FileNotFoundException {
        String[] headers = {"id", "title", "description", "coins", "image", "mentor"};
        Object[][] data = new Object[newList.size()][headers.length];

        for (int i = 0; i < newList.size(); i++) {
            Quest quest = newList.get(i);
            data[i][0] = quest.getId();
            data[i][1] = quest.getTitle();
            data[i][2] = quest.getDescription();
            data[i][3] = quest.getPrice();
            data[i][4] = quest.getImageSrc();
            data[i][5] = quest.getMentorNameAndSurname();
        }
        System.out.println("All quests with mentors");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }


}
