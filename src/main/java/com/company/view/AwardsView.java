package com.company.view;

import com.company.model.Award;
import com.jakewharton.fliptables.FlipTableConverters;

import java.util.List;

public class AwardsView {
    public static void awardsManagmentMenu() {
        System.out.println("(1) View awards table\n" +
                "(2) Create award\n" +
                "(3) Update award data\n" +
                "(4) Delete award\n" +
                "(0) Quit");
    }

    public static void updateAwardModes() {
        System.out.println("What do you want to edit? \n");
        System.out.println("(1) Title\n" +
                "(2) Description\n" +
                "(3) Price\n" +
                "(4) Id of award creator\n" +
                "(0) Back to the Award Managment Menu");
    }

    public static void allAwardsByList(List<Award> awards) {
        String[] headers = {"id", "title", "description", "coins", "image", "creation_date", "creator"};
        Object[][] data = new Object[awards.size()][headers.length];

        for (int i = 0; i < awards.size(); i++) {
            Award award = awards.get(i);
            data[i][0] = award.getId();
            data[i][1] = award.getTitle();
            data[i][2] = award.getDescription();
            data[i][3] = award.getPrice();
            data[i][4] = award.getImageSrc();
            data[i][5] = award.getDataCreation();
            data[i][6] = award.getMentorId();
        }
        System.out.println("All my awards");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }
}
