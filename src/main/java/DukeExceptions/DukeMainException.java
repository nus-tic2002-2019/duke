package DukeExceptions;

public class DukeMainException {

    //command entered without description
    public static class nullDescription extends DukeBaseException {

        private static final long serialVersionUID = 7845420361544789423L;
        public nullDescription(String message) {

            super(message);
        }
    }

    //invalid command
    public static class invalidInput extends DukeBaseException {


        private static final long serialVersionUID = -902479303575155928L;
        public invalidInput(String message) {

            super(message);
        }
    }

    //deadline entered without date
    public static class deadDate extends DukeBaseException {

        private static final long serialVersionUID = 3459056459186851874L;
        public deadDate(String message) {

            super(message);
        }
    }

    //event entered without time
    public static class eventTime extends DukeBaseException {

        private static final long serialVersionUID = -8995742482064551983L;
        public eventTime(String message) {

            super(message);
        }
    }
}
