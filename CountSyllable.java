package readability;

    public class CountSyllable {

        static String[] addSyllableArray = { "ia", "riet", "dien", "iu", "io", "ii", "[aeiouym]bl$", "[aeiou]{3}", "^mc", "ism$",
                "[^aeiouy][^aeiouy]l$", "[^l]lien", "^coa[dglx].", "[^gq]ua[^auieo]", "dnt$" };
        static String[] subtractSyllableArray = { "cial", "tia", "cius", "cious", "giu", "ion", "iou", "sia$", ".ely$" };

        public static int count(String string) {
            string = string.toLowerCase();
            string = string.replaceAll("'", " ");

            if (string.equals("i"))
                return 1;
            if (string.equals("a"))
                return 1;

            if (string.endsWith("e")) {
                string = string.substring(0, string.length() - 1);
            }

            String[] phonemes = string.split("[^aeiouy]+");

            int syllableCount = 0;
            for (int i = 0; i < subtractSyllableArray.length; i++) {
                String syllable = subtractSyllableArray[i];
                if (string.matches(syllable)) {
                    syllableCount--;
                }
            }
            for (int i = 0; i < addSyllableArray.length; i++) {
                String syllable = addSyllableArray[i];
                if (string.matches(syllable)) {
                    syllableCount++;
                }
            }
            if (string.length() == 1) {
                syllableCount++;
            }

            for (int i = 0; i < phonemes.length; i++) {
                if (phonemes[i].length() > 0)
                    syllableCount++;
            }

            if (syllableCount == 0) {
                syllableCount = 1;
            }

            return syllableCount;
        }

    }




