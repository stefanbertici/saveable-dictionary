package dictionary;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveableDictionary {

    private List<String> words;
    private List<String> translations;
    private String fileName;

    public SaveableDictionary() {
        this.words = new ArrayList<>();
        this.translations = new ArrayList<>();
    }
    
    public SaveableDictionary(String file) {
        this();
        this.fileName = file;
    }

    public void add(String word, String translation) {
        if (!this.words.contains(word)) {
            this.words.add(word);
            this.translations.add(translation);
        }
    }

    public String translate(String word) {
        if (this.words.contains(word)) {
            return this.translations.get(this.words.indexOf(word));
        } else if (this.translations.contains(word)) {
            return this.words.get(this.translations.indexOf(word));
        } else {
            return null;
        }
    }

    public void delete(String word) {
        if (this.words.contains(word)) {
            this.translations.remove(this.words.indexOf(word));
            this.words.remove(word);
        }
        
        if (this.translations.contains(word)) {
            this.words.remove(this.translations.indexOf(word));
            this.translations.remove(word);
        }
    }
    
    public boolean load() {
        try {
            Scanner scanner  = new Scanner(new File(this.fileName));
            
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                this.add(parts[0], parts[1]);
            }
            
            return true;
        }
        
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean save() {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            for (String word : this.words) {
                writer.println(word + ":" + this.translations.get(this.words.indexOf(word)));
            }
            
            writer.close();
            return true;
        }
        
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
