package codes;

import java.util.Scanner;

public class MainDrive {
	public static void main(String[] args) {

//	 6개의 숫자 입력받기.

		Scanner S = new Scanner(System.in);

		int[] myNumbers = new int[6];

		for (int i = 0; i < 6; i++) {

			while (true) {
				System.out.println((i + 1) + "번째 숫자 : ");
				int inputNum = S.nextInt();

				boolean isRangeOk = (1 <= inputNum) && (inputNum <= 45);

				boolean isDuplOk = true;

				for (int myNum : myNumbers) {
					if (myNum == inputNum) {
						isDuplOk = false;
						break;
					}
				}

				if (isRangeOk && isDuplOk) {

					myNumbers[i] = inputNum;

					break;
				}

			}

		}

		int[] winNumbers = new int[6];

		for (int i = 0; i < winNumbers.length; i++) {

			while (true) {

//				Math.random() =>    1 <= 랜덤값*45+1 < 46
//				cf) Random 클래스를 활용해도 됨.
				int randomNum = (int) (Math.random() * 45 + 1);

				boolean isDuplOk = true;

				for (int num : winNumbers) {
					if (num == randomNum) {
						isDuplOk = false;
						break;
					}
				}

				if (isDuplOk) {
					winNumbers[i] = randomNum;
					break;
				}

			}

		}

//		보너스 번호 뽑기
//		제약사항 : 1~45 중 하나. 기존의 당첨번호와 중복 X

		int bonusNum = 0;

		while (true) {

			int randomNum = (int) (Math.random() * 45 + 1);

			boolean isDuplOk = true;

			for (int num : winNumbers) {
				if (randomNum == num) {
					isDuplOk = false;
					break;
				}
			}

			if (isDuplOk) {

				bonusNum = randomNum;
				break;

			}

		}

//		임시 당첨 번호 선정 -> 당첨 등수 로직 테스트용
//		winNumbers[0] = 10;
//		winNumbers[1] = 11;
//		winNumbers[2] = 20;
//		winNumbers[3] = 21;
//		winNumbers[4] = 30;
//		winNumbers[5] = 31;
//		
//		bonusNum = 45;
		

//		랜덤으로 만들어진 당첨번호들을 > 작은 수 ~ 큰 수로 정리.

		for (int i = 0; i < winNumbers.length; i++) {

			for (int j = 0; j < winNumbers.length - 1; j++) {

				if (winNumbers[j] > winNumbers[j + 1]) {

					int backUp = winNumbers[j];

					winNumbers[j] = winNumbers[j + 1];

					winNumbers[j + 1] = backUp;

				}

			}

		}

//		정리 된 당첨번호를 확인

		for (int num : winNumbers) {
			System.out.println(num);
		}

//		보너스번호도 확인

		System.out.println("보너스번호 : " + bonusNum);

//		맞춘 갯수 확인

		int correctCount = 0;

		for (int myNum : myNumbers) {

			for (int winNum : winNumbers) {

				if (myNum == winNum) {
					correctCount++;
				}

			}

		}

//		등수 판정

		if (correctCount == 6) {
			System.out.println("1등");
		} else if (correctCount == 5) {
//			보너스번호 로직
			boolean isBonusCorrect = false;

			for (int myNum : myNumbers) {

				if (myNum == bonusNum) {
					isBonusCorrect = true;
					break;
				}

			}

			if (isBonusCorrect) {
				System.out.println("2등");
			} else {
				System.out.println("3등");
			}

		} else if (correctCount == 4) {

			System.out.println("4등");
		} else if (correctCount == 3) {
			System.out.println("5등");
		} else {
			System.out.println("낙첨");
		}

	}

}
