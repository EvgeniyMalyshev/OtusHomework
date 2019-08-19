import java.util.ArrayList;
import java.util.List;


class Statistic{
    List<Float> youngPauses = new ArrayList<>();
    List<Float> elderPauses = new ArrayList<>();
    List<Float> otherPauses = new ArrayList<>();

    int getCountPauses() {
        return youngPauses.size() + elderPauses.size() + otherPauses.size();
    }

    Float getTimeAllPauses() {
        return getTimePausesYoung() + getTimePausesFull() + getTimePausesOther();
    }

    Float getTimePausesYoung(){
        return youngPauses.stream().reduce(0f, (x,y) -> {return  x + y;});
    }

    Float getTimePausesFull(){
        return elderPauses.stream().reduce(0f, (x, y) -> {return  x + y;});
    }

    Float getTimePausesOther(){
        return otherPauses.stream().reduce(0f, (x,y) -> {return  x + y;});
    }

    Float getAveragePausesTime() {
        return getTimeAllPauses()/getCountPauses();
    }

    Float getAveragePausesYoungTime() {
        return getTimePausesYoung()/youngPauses.size();
    }

    Float getMinPauseYoungTime() {
        return youngPauses.stream().min(Float::compareTo).get();
    }

    Float getMaxPausesYoungTime() {
        return youngPauses.stream().max(Float::compareTo).get();
    }

    Float getAveragePausesFullTime() {
        return getTimePausesFull()/ elderPauses.size();
    }

    Float getMinPauseFullTime() {
        return elderPauses.stream().min(Float::compareTo).orElse(0f);
    }

    Float getMaxPausesFullTime() {
        return elderPauses.stream().max(Float::compareTo).orElse(0f);
    }

    Float getAveragePausesOtherTime() {
        return getTimePausesOther()/otherPauses.size();
    }

    Float getMinPauseOtherTime() {
        return otherPauses.stream().min(Float::compareTo).orElse(null);
    }

    Float getMaxPausesOtherTime() {
        return otherPauses.stream().max(Float::compareTo).orElse(null);
    }
}
