class MorseTree {
    private Node root;

    // Child node and variable definitions
    private static class Node {
        char letter;
        Node dot;  
        Node dash;  

        Node(char letter) {
            this.letter = letter;
            this.dot = null;
            this.dash = null;
        }
    }

    // Constructor   
    public MorseTree() {
        root = new Node(' ');  
        addMorseCode();
    }

    private void addMorseCode() {
        String[][] morseCodes = {
            {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
            {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
            {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
            {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
            {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
            {"Z", "--.."}, {"1", ".----"}, {"2", "..---"}, {"3", "...--"},
            {"4", "....-"}, {"5", "....."}, {"6", "-...."}, {"7", "--..."},
            {"8", "---.."}, {"9", "----."}, {"0", "-----"}, {".", ".-.-.-"},
            {",", "--..--"}, {"?", "..--.."}, {"'", ".----."}, {"!", "-.-.--"},
            {"/", "-..-."}, {"(", "-.--."}, {")", "-.--.-"}, {"&", ".-..."},
            {":", "---..."}, {";", "-.-.-."}, {"=", "-...-"}, {"+", ".-.-."},
            {"-", "-....-"}, {"_", "..--.-"}, {"\"", ".-..-."}, {"$", "...-..-"},
            {"@", ".--.-."}, {" ", " "}
        };

        for (String[] entry : morseCodes) {
            addLetter(entry[0].charAt(0), entry[1]);
        }
    }

    private void addLetter(char letter, String morseCode) {
        Node current = root;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == '.') {
                if (current.dot == null) {
                    current.dot = new Node(' ');
                }
                current = current.dot;
            } else if (symbol == '-') {
                if (current.dash == null) {
                    current.dash = new Node(' ');
                }
                current = current.dash;
            }
        }
        current.letter = letter;
    }

    private char decodeLetter(String morseCode) {
        Node current = root;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == '.') {
                if (current.dot == null) {
                    return '?';  
                }
                current = current.dot;
            } else if (symbol == '-') {
                if (current.dash == null) {
                    return '?'; 
                }
                current = current.dash;
            } else {
                return '?';  
            }
        }
        return current.letter;
    }

    public String decode(String morseCodeMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        String[] words = morseCodeMessage.trim().split(" {2,}| / ");
        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            String[] letters = word.split(" ");
            for (String letterCode : letters) {
                if (!letterCode.isEmpty()) {
                    char decodedLetter = decodeLetter(letterCode);
                    decodedMessage.append(decodedLetter);
                }
            }
            if (w < words.length - 1) {
                decodedMessage.append(' ');  // Add space between words
            }
        }
        return decodedMessage.toString();
    }

    // Method to get the Morse tree structure as a string
    public String getTreeStructure() {
        StringBuilder builder = new StringBuilder();
        buildTreeStructureString(root, 0, builder);
        return builder.toString();
    }

    private void buildTreeStructureString(Node node, int depth, StringBuilder builder) {
        if (node == null) {
            return;
        }
        String indent = "  ".repeat(depth);
        builder.append(indent).append(node.letter == ' ' ? "(empty)" : node.letter).append("\n");
        buildTreeStructureString(node.dot, depth + 1, builder);
        buildTreeStructureString(node.dash, depth + 1, builder);
    }
}


// Split letters within a word by single spaces and words by multiple spaces or " / "