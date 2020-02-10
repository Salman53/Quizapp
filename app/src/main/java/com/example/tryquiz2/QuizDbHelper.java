package com.example.tryquiz2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";


    private static final int DB_VERSION = 3;

    private static final String TABLE_NAME = "TQ";

    private static final String UID = "_UID";

    private static final String QUESTION = "QUESTION";

    private static final String OPTA = "OPTA";

    private static final String OPTB = "OPTB";

    private static final String OPTC = "OPTC";

    private static final String OPTD = "OPTD";

    private static final String ANSWER = "ANSWER";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuizDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<Questions> arraylist = new ArrayList<>();

        arraylist.add(new Questions("All caps to selected text in MS Word?", "Ctrl+shift+A", "Alt+shift+A", "shift+A", "None of these", "Ctrl+shift+A"));

        arraylist.add(new Questions("Apply bullet list in MS Word?", "Alt+shift+A", "Ctrl+shift+L", "Ctrl+shift+C", "Ctrl+shift+B", "Ctrl+shift+L"));

        arraylist.add(new Questions("Apply auto format in MS Word?", "Alt+Ctrl+K", "Alt+shift+A", "Ctrl+shift+A", "None of these", "Alt+Ctrl+K"));

        arraylist.add(new Questions("To bold the text in MS Word?", " Ctrl+B", "Alt+B", "Shift+B", "None of these", "Ctrl+B"));

        arraylist.add(new Questions("To cancel the text in MS Word?", "Delete ", "End", "ESC", "None of these", "ESC"));

        arraylist.add(new Questions("In which view Headers and Footers are visible?", "Normal View", "Page Layout View", "Print Layout View", "Draft View", "Print Layout View"));

        arraylist.add(new Questions("The process of removing unwanted part of an image is called?", "Hiding", "Bordering", "Cropping", "Cutting", "Cropping"));

        arraylist.add(new Questions("To apply center alignment to a paragraph we can press?", "Ctrl + S", "Ctrl + C", "Ctrl + C + A", "Ctrl + E", "Ctrl + E"));

        arraylist.add(new Questions("The space left between the margin and the start of a paragraph is called ?", "Spacing", "Gutter", "Indentation", "Alignment", "Indentation"));

        arraylist.add(new Questions("Text-styling feature of MS word is?", "WordColor", "WordFont", " WordArt", "WordFill", "WordArt"));

        arraylist.add(new Questions("Which items are placed at the end of a document ?", "Footer", "Foot Note", "End Note", "Header", "End Note"));

        arraylist.add(new Questions("To change line height to 1.5 we use shortcut key ?", "Ctrl+1", "Ctrl + 2", "Ctrl + 3", "Ctrl + 5", "Ctrl + 5"));

        arraylist.add(new Questions("A number of letter that appears little above the normal text is called?", "Superscript", "Subscript", "Supertext", "Toptext", "Superscript"));

        arraylist.add(new Questions("Who was the founder of company Microsoft", "Bill Gates", "Bill Clinton", "Jhon rio", "Steve jobs", "Bill Gates"));

        arraylist.add(new Questions("We can insert a page number at ?", "Header", "Footer", "Both A and B", "None", "Both A and B"));

        arraylist.add(new Questions("Which one can be used as watermark in a word document?", "Text", "Image", "Both A and B", "None", "Both A and B"));

        arraylist.add(new Questions("Which item appears dimly behind the main body text ?", "Water Color", "Background", "Watermark", "Back Color", "Watermark"));

        arraylist.add(new Questions("Which feature starts a new line whenever a word or sentence reached a border?", "Text Line", "New Line", "Text Wrapping", "Text Align", "Text Wrapping"));

        arraylist.add(new Questions("The direction of a rectangular page for viewing and printing is called ?", "Orientation", "Direction", "Print Layout", "Preview", "Orientation"));

        arraylist.add(new Questions("We can remove / hide border of a shape by selecting  ?", "No Line", "No Outline", "White Line", "No Border", "No Outline"));

        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<Questions> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Questions question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<Questions> getAllOfTheQuestions() {

        List<Questions> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            Questions question = new Questions();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
