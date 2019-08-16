import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GcStarter {

    void printStat(String filePath){
        Statistic stat = new Statistic();
        Pattern patternForYoung = Pattern.compile(".*\\[(\\d{1,5})\\.\\d{0,4}s.*(Pause Young).* (\\d{1,5}\\.\\d{0,4})ms");
        Pattern patternForElder = Pattern.compile(".*\\[(\\d{1,5})\\.\\d{0,4}s.*(Pause Full).* (\\d{1,5}\\.\\d{0,4})ms");
        Pattern patternForOther = Pattern.compile(".*\\[(\\d{1,5})\\.\\d{0,4}s.*(Pause ((Remark)|(Cleanup))).* (\\d{1,5}\\.\\d{0,4})ms");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcherYoung = patternForYoung.matcher(line);
                Matcher matcherElder = patternForElder.matcher(line);
                Matcher matcherOther = patternForOther.matcher(line);
                if(matcherYoung.find() && Integer.valueOf(matcherYoung.group(1)) <= 250){
                    stat.youngPauses.add(Float.valueOf(matcherYoung.group(3)));
                } else
                if(matcherElder.find() && Integer.valueOf(matcherElder.group(1)) <= 250){
                    stat.elderPauses.add(Float.valueOf(matcherElder.group(3)));
                } else
                if(matcherOther.find() && Integer.valueOf(matcherOther.group(1)) <= 250){
                    stat.otherPauses.add(Float.valueOf(matcherOther.group(6)));
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не считался\n" + e.getMessage());
        }
        System.out.println("Файл - " + filePath);
        System.out.println("Количество пауз = " + stat.getCountPauses()
                + " || Общее время пауз GC = " + stat.getTimeAllPauses()
                + " || Среднее время пауз = " + stat.getAveragePausesTime()
        );
        System.out.println("Количество Pause Young = " + stat.youngPauses.size()
                + " || Общее время Pause Young = " + stat.getTimePausesYoung()
                + " || Среднее время Pause Young = " + stat.getAveragePausesYoungTime()
                + " || Минимальная Pause Young = " + stat.getMinPauseYoungTime()
                + " || Максимальная Pause Young = " + stat.getMaxPausesYoungTime()
        );
        System.out.println("Количество Pause Full = " + stat.elderPauses.size()
                + " || Общее время Pause Full = " + stat.getTimePausesFull()
                + " || Среднее время Pause Full = " + stat.getAveragePausesFullTime()
                + " || Минимальная Pause Full = " + stat.getMinPauseFullTime()
                + " || Максимальная Pause Full = " + stat.getMaxPausesFullTime()
        );
        System.out.println("Количество иных пауз (remark + cleanup) = " + stat.otherPauses.size()
                + " || Общее время иных пауз = " + stat.getTimePausesOther()
                + " || Среднее время иных пауз = " + stat.getAveragePausesOtherTime()
                + " || Минимальная иных пауз = " + stat.getMinPauseOtherTime()
                + " || Максимальная иных пауз = " + stat.getMaxPausesOtherTime()
        );
        System.out.println("=======================================================================");
    }
}
