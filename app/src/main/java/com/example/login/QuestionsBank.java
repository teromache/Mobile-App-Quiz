package com.example.login;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    private static List<QuestionsList> mobileQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();


        final QuestionsList question1 = new QuestionsList("A programming environment that has been packaged as an application program.", "Programming Language", "Android SDK", "Virtual Studio", "Integrated Development Environment", "Integrated Development Environment");
        final QuestionsList question2 = new QuestionsList("Which XML element of the Android manifest indicates security access your application will need to be allowed to function properly?", "Uses-permission", "Instrumentation", "Uses-SDK", "Application", "Uses-permission");
        final QuestionsList question3 = new QuestionsList("Which file contains GUI layout information like a list of buttons, text boxes?", "AndroidManifest.xml", "build.xml", "res/layout/main/xml", "assets", "res/layout/main.xml");
        final QuestionsList question4 = new QuestionsList("During an Activity life-cycle, what is the first callback method invoked by the system?", "onStop()", "onStart()", "onCreate()", "onRestore()", "onCreate()");
        final QuestionsList question5 = new QuestionsList("What is a valid property of a label widget?", "android:typeface", "TextView", "layout/main.xml", "LabelView", "android:typeface");
        final QuestionsList question6 = new QuestionsList("The simplest Android Widget is a label. What is its name in the layout file?", "TextView", "android-label", "label.xml", "LabelView", "TextView");


        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    private static List<QuestionsList> webQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();


        final QuestionsList question1 = new QuestionsList("What does HTML stand for?", "Hyperlinks and Text Markup Language", "Hyper Text Markup Language", "Home Tool Markup Language", "None of these", "Hyper Text Markup Language");
        final QuestionsList question2 = new QuestionsList("Choose the correct HTML tag for the largest heading.", "<heading>", "<h6>", "<head>", "<h1>", "<h1>");
        final QuestionsList question3 = new QuestionsList("Which of these tags are all <table> tags?", "<table><head><tfoot>", "<table><tr><td>", "<thead><body><tr>", "<table><tr><tt>", "<table><tr><td>");
        final QuestionsList question4 = new QuestionsList("What is the correct HTML for inserting an image?", "<img alt=\"MyImage\">image.gif</img>", "<img src=\"image.gif\" alt=\"MyImage\">", "<img href=\"image.gif\" alt=\"MyImage\">", "<image src=\"image.gif\" alt=\"MyImage\">", "<img src=\"image.gif\" alt=\"MyImage\">");
        final QuestionsList question5 = new QuestionsList("How can you make a numbered list?", "<ol>", "<ul>", "<dl>", "<list>", "<ol>");
        final QuestionsList question6 = new QuestionsList("What is the correct HTML for making a checkbox?", "<checkbox>", "<input type=\"checkbox\">", "<check>", "<input type=\"check\">", "<input type=\"checkbox\">");

        // add all questions to List<QuestionsList>
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    private static List<QuestionsList> algoQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();


        final QuestionsList question1 = new QuestionsList("In a selection sort structure, there is/are?", "Two separate for loops", "Three for loops, all separate", "Two for loops, one nested in the other", "A for loop nested inside a while loop", "Two for loops, one nested in the other");
        final QuestionsList question2 = new QuestionsList("Which one of the following is the first step in a selection sort algorithm?", "The minimum value in the list is found", "The maximum value in the list is found", "Adjacent elements are swapped", "None of these", "The minimum value in the list is found");
        final QuestionsList question3 = new QuestionsList("What is the time complexity of adding an item in front of a LinkedList?", "O(logn)", "O(1)", "O(n^2)", "O(2^n)", "O(1)");
        final QuestionsList question4 = new QuestionsList("What are the three algorithm constructs?", "Sequence,selection,repetition", "input,output,process", "Input/output,decision,terminator", "Loop,input/output,process", "Sequence,selection,repetition");
        final QuestionsList question5 = new QuestionsList("What is an algorithm?", "A flowchart", "Step by step instruction to solve problem", "A pseudocode", "A decision", "Step by step instruction to solve problem");
        final QuestionsList question6 = new QuestionsList("What is the time complexity of the recursive Binary Search algorithm?", "O(n)", "O(2^n)", "O(logn)", "O(nlogn)", "O(logn)");


        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    private static List<QuestionsList> aiQuestions() {

        final List<QuestionsList> questionsLists = new ArrayList<>();


        final QuestionsList question1 = new QuestionsList("Which of these is a tool used in Artificial Intelligence?", "Neural Networks", "Input", "Design", "None of these", "Neural Networks");
        final QuestionsList question2 = new QuestionsList("Which of the languages below are used to code a robot?", "Python", "Machine Learning", "Java", "Computer Vision", "Python");
        final QuestionsList question3 = new QuestionsList("When was Artificial Intelligence first introduced?", "1957", "1956", "1936", "2001", "1956");
        final QuestionsList question4 = new QuestionsList("Which of the applications below is prominent in AI?", "Honesty", "Emotional Management", "Speech Recognition", "None of these", "Speech Recognition");
        final QuestionsList question5 = new QuestionsList("In the following options, which are python libraries which are used for data analysis and scientific computations?", "Numpy", "Scipy", "Pandas", "All the above", "All the above");
        final QuestionsList question6 = new QuestionsList("Which method is used to display a warning message in Python?", "Tkinter.message.showmessage('message here')", "Tkinter.message.sshowwarning('message here')", "Tkinter.messagebox.showwarning('message here')", "Tkinter.messagebox.showmessage('message here')", "Tkinter.messagebox.showwarning('message here')");


        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;
    }

    public static List<QuestionsList> getQuestions(String selectedTopicName) {
        switch (selectedTopicName) {
            case "Mobile App":
                return mobileQuestions();
            case "Web App":
                return webQuestions();
            case "Algorithm":
                return algoQuestions();
            default:
                return aiQuestions();
        }
    }
}
