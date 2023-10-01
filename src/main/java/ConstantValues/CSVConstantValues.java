package ConstantValues;

import static ConstantValues.Config.getValueFromNestedMap;

public class CSVConstantValues {
    public static final String csvFilePath = getValueFromNestedMap("APP.Footballer.csvFilePath");
    public static final String player_country = getValueFromNestedMap("APP.Footballer.player_country");
    public static final String first_name = getValueFromNestedMap("APP.Footballer.first_name");
    public static final String last_name = getValueFromNestedMap("APP.Footballer.last_name");
    public static final String goals_scored = getValueFromNestedMap("APP.Footballer.goals_scored");
    public static final String champions_league_matches_played = getValueFromNestedMap("APP.Footballer.champions_league_matches_played");
    public static final String english_league_matches_played = getValueFromNestedMap("APP.Footballer.english_league_matches_played");
    public static final String minutes_played = getValueFromNestedMap("APP.Footballer.minutes_played");
    public static final String assists = getValueFromNestedMap("APP.Footballer.assists");
    public static final String tackles = getValueFromNestedMap("APP.Footballer.tackles");
    public static final String age = getValueFromNestedMap("APP.Footballer.age");

}