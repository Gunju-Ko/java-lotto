package dto;

import java.text.MessageFormat;
import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000) {
        @Override
        public boolean shouldMatchBonus() {
            return true;
        }
    },
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    FAIL(0, 0);

    private static final String TO_STRING_FORMAT = "{0}개 일치{1} ({2}원)";
    private final int countOfMatch;
    private final int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < 0 || countOfMatch > 6) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(Rank.values())
                     .filter(r -> r.isSatisfyRankCondition(countOfMatch, matchBonus))
                     .findFirst()
                     .orElse(FAIL);
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public boolean isFail() {
        return this == Rank.FAIL;
    }

    public boolean shouldMatchBonus() {
        return false;
    }

    private boolean isSatisfyRankCondition(int countOfMatch, boolean matchBonus) {
        return getCountOfMatch() == countOfMatch && (!shouldMatchBonus() || matchBonus);
    }

    private String getMatchBonusMessage() {
        if (shouldMatchBonus()) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    @Override
    public String toString() {
        return MessageFormat.format(TO_STRING_FORMAT,
                                    countOfMatch,
                                    getMatchBonusMessage(),
                                    winningMoney);
    }
}
