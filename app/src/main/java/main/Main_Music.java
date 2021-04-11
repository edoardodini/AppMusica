package main;

import java.util.Arrays;
import java.util.List;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class Main_Music {

	public static void main(String[] args) {
		Note noteDo = new Note("DO");
		Note noteRe = new Note("RE");
		Note noteLa = new Note("LA");
		Interval tone = new Interval(1, 0);
		Interval halfTone = new Interval(0, 1);
		Scale majorScale = new Scale(Arrays.asList(tone, tone, halfTone, tone, tone, tone, halfTone));
		Scale minorScale = new Scale(Arrays.asList(tone, halfTone, tone, tone, halfTone, tone, tone));
		List<Note> doMajorScale;
		List<Note> reMajorScale;
		List<Note> doMinorScale;
		List<Note> laMinorScale;
		doMajorScale = majorScale.getNotes(noteDo);
		reMajorScale = majorScale.getNotes(noteRe);
		doMinorScale = minorScale.getNotes(noteDo);
		laMinorScale = minorScale.getNotes(noteLa);
		System.out.print("scala maggiore di DO :");
		for (Note note : doMajorScale) {
			System.out.print(note.getNote() + ", ");
		}
		System.out.print("\nscala minore di DO :");
		for (Note note : doMinorScale) {
			System.out.print(note.getNote() + ", ");
		}
		System.out.print("\nscala maggiore di RE :");
		for (Note note : reMajorScale) {
			System.out.print(note.getNote() + ", ");
		}
		System.out.print("\nscala minore di LA :");
		for (Note note : laMinorScale) {
			System.out.print(note.getNote() + ", ");
		}
	}

}
