package principalPACK.clase.work;

public class Conometru {
    int secunde;
    int minute;
    int ore;
    static String format;
    boolean work;

    public Conometru() {
        this.secunde = 0;
        this.minute = 0;
        this.ore = 0;
        start();
    }

    public void start() {
        work = true;
        incepe();
    }

    public void stop() {
        work = false;
    }

    public void continua() {
        work = true;
    }

    private String formatData(int ore, int minute, int secunde) {
        String sec = String.valueOf(secunde);
        String min = String.valueOf(minute);
        String or = String.valueOf(ore);
        if (sec.length() == 1)
            sec = "0" + sec;
        if (min.length() == 1)
            min = "0" + min;
        if (or.length() == 1)
            or = "0" + or;
        return or + ":" + min + ":" + sec;
    }

    public String afisTimp() {
        return format;
    }

    private void incepe() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (work) {
                            if (secunde == 60) {
                                secunde = 0;
                                minute++;
                            }
                            if (minute == 60) {
                                minute = 0;
                                ore++;
                            }
                            format = formatData(ore, minute, secunde);
                            secunde++;
                        }
                        //System.out.println(format);
                        sleep(1000);
                    }
                } catch (Exception e) {
                }
            }

        };
        thread.setDaemon(true);
        thread.start();

    }
}

