import java.util.ArrayList;

public class getMinMax implements ClientRequest<ArrayList<Integer>> {

    ArrayList<Integer> returnList;
    public getMinMax(ArrayList<Integer> returnList) {
        this.returnList = returnList;
    }

    @Override
    public ArrayList<Integer> handle(Storage storage) {
        return null;
    }

    @Override
    public ArrayList<Integer> handle(Storage storage, ArrayList<Integer> arrayList) {
        returnList.addAll(arrayList);
        return returnList;
    }


    @Override
    public MessageType getType() {
        return MessageType.GET_MIN_MAX;
    }
}