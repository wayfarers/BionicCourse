package lesson12;

import java.io.IOException;

public class DictMain {
	public static void main(String[] args) throws IOException {
		DictEx2 dict = new DictEx2();
		dict.init2();
		System.out.println(dict.getTranslate("Remove"));
	}
}
