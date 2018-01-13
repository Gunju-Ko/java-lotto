package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static common.Constant.COUNT_OF_LOTTO_NUMBERS;

public class WinningNumbers {
    private final List<LottoNumber> winningNumbers;

    public WinningNumbers(int[] winningNumbers) {
        checkSizeOfWinningNumbers(winningNumbers);
        checkDuplicatedWinningNumbers(winningNumbers);
        this.winningNumbers = convertIntArrayToLottoNumbers(winningNumbers);
    }

    private void checkSizeOfWinningNumbers(int[] winningNumbers) {
        if (winningNumbers.length != COUNT_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException();
        }
    }

    private void checkDuplicatedWinningNumbers(int[] winningNumbers) {
        if (hasDuplicatedLottoNumbers(winningNumbers)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasDuplicatedLottoNumbers(int[] winningNumbers) {
        return winningNumbers.length > sizeOfDistinctWinningNumbers(winningNumbers);
    }

    private int sizeOfDistinctWinningNumbers(int[] winningNumbers) {
        return IntStream.of(winningNumbers)
                        .boxed()
                        .collect(Collectors.toSet())
                        .size();
    }

    private List<LottoNumber> convertIntArrayToLottoNumbers(int[] winningNumbers) {
        return Arrays.stream(winningNumbers)
                     .distinct()
                     .mapToObj(LottoNumber::new)
                     .collect(Collectors.toList());
    }

    public boolean contain(LottoNumber number) {
        return winningNumbers.stream()
                             .anyMatch(i -> i.equals(number));
    }
}
