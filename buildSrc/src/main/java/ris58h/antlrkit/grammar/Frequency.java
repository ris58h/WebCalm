package ris58h.antlrkit.grammar;

enum Frequency {
    ZERO_OR_ONE, ZERO_OR_MORE, ONE, ONE_OR_MORE;

    Frequency or(Frequency other) {
        switch (this) {
            // rule? | rule? ; rule? | rule* ; rule? | rule ; rule? | rule+
            // (rule?)? ; (rule?)* ; (rule?) ; (rule?)+
            case ZERO_OR_ONE: switch (other) {
                case ZERO_OR_ONE: return Frequency.ZERO_OR_ONE;
                case ZERO_OR_MORE: return Frequency.ZERO_OR_MORE;
                case ONE: return Frequency.ZERO_OR_ONE;
                case ONE_OR_MORE: return Frequency.ZERO_OR_MORE;
                default: throw new IllegalArgumentException();
            }
            // rule* | rule? ; rule* | rule* ; rule* | rule ; rule* | rule+
            // (rule*)? ; (rule*)* ; (rule*) ; (rule*)+
            case ZERO_OR_MORE: return Frequency.ZERO_OR_MORE;
            // rule | rule? ; rule | rule* ; rule | rule ; rule | rule+
            // (rule)? ; (rule)* ; (rule) ; (rule)+
            case ONE: return other;
            // rule+ | rule? ; rule+ | rule* ; rule | rule+ ; rule+ | rule+
            // (rule+)? ; (rule+)* ; (rule+) ; (rule+)+
            case ONE_OR_MORE: switch (other) {
                case ZERO_OR_ONE: return Frequency.ZERO_OR_MORE;
                case ZERO_OR_MORE: return Frequency.ZERO_OR_MORE;
                case ONE: return Frequency.ONE_OR_MORE;
                case ONE_OR_MORE: return Frequency.ONE_OR_MORE;
                default: throw new IllegalArgumentException();
            }
            default: throw new IllegalArgumentException();
        }
    }

    Frequency and(Frequency other) {
        switch (this) {
            // rule? rule? ; rule? rule* ; rule? rule ; rule? rule+
            case ZERO_OR_ONE: switch (other) {
                case ZERO_OR_ONE: return Frequency.ZERO_OR_MORE;
                case ZERO_OR_MORE: return Frequency.ZERO_OR_MORE;
                case ONE: return Frequency.ONE_OR_MORE;
                case ONE_OR_MORE: return Frequency.ONE_OR_MORE;
                default: throw new IllegalArgumentException();
            }
            // rule* rule? ; rule* rule* ; rule* rule ; rule* rule+
            case ZERO_OR_MORE: switch (other) {
                case ZERO_OR_ONE: return Frequency.ZERO_OR_MORE;
                case ZERO_OR_MORE: return Frequency.ZERO_OR_MORE;
                case ONE: return Frequency.ONE_OR_MORE;
                case ONE_OR_MORE: return Frequency.ONE_OR_MORE;
                default: throw new IllegalArgumentException();
            }
            // rule rule? ; rule rule* ; rule rule ; rule rule+
            case ONE: return Frequency.ONE_OR_MORE;
            // rule+ rule? ; rule+ rule* ; rule rule+ ; rule+ rule+
            case ONE_OR_MORE: return Frequency.ONE_OR_MORE;
            default: throw new IllegalArgumentException();
        }
    }
}
