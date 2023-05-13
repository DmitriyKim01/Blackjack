// Helper enum class for: Card
public enum Number {
    TWO     (2){
        public String toString(){
            return "2";
        }
    },
    THREE   (3){
        public String toString(){
            return "3";
        }
    },
    FOUR    (4){
        public String toString(){
            return "4";
        }
    },
    FIVE    (5){
        public String toString(){
            return "5";
        }
    },
    SIX     (6){
        public String toString(){
            return "6";
        }
    },
    SEVEN   (7){
        public String toString(){
            return "7";
        }
    },
    EIGTH   (8){
        public String toString(){
            return "8";
        }
    },
    NINE    (9){
        public String toString(){
            return "9";
        }
    },
    TEN     (10){
        public String toString(){
            return "10";
        }
    },
    JACK    (10){
        public String toString(){
            return "J";
        }
    },
    QUEEN   (10){
        public String toString(){
            return "Q";
        }
    },
    KING    (10){
        public String toString(){
            return "K";
        }
    },
    ACE     (11){
        public String toString(){
            return "A";
        }
    };

    private final int value;
    Number(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
