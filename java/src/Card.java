//扑克牌
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Card{
    private int number;
    private String color;
    public List<Card> createCard(){
        List<Card> pork = new ArrayList<Card>();
        String[] colors = new String[]{"红花","黑桃","方片","梅花"};
        for(int i=0;i<13;i++){
            for(int j=0;j<colors.length;j++){
                Card card = new Card();
                card.number = i;
                card.color = colors[j];
                pork.add(card);
            }
        }
        return pork;
    }

    public List<Card> randomSelect(int number){
        List<Card> result = new ArrayList<Card>();
        List<Card> pork = createCard();
        Random random = new Random();
        for(int i=0;i<number;i++){
            int index = random.nextInt(pork.size());
            //System.out.println("the index: "+index);
            result.add(pork.get(index));
        }
        return result;
    }

    public void printPork(List<Card> pork){
        for(int i=0;i<pork.size();i++){
            System.out.println(pork.get(i).color+pork.get(i).number);
        }
    }

    public boolean judge( List<Card> pork){
        if( pork == null || pork.size() <=1){
            return false;
        }
        int[] nums = new int[pork.size()];
        nums[0] = pork.get(0).number;
        String col = pork.get(0).color;
        for(int i=1;i<pork.size();i++){
            if( !pork.get(i).color.equals(col) ){
                System.out.println("不是同一花色");
                return false;
            }
            nums[i] = pork.get(i).number;

        }
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if( nums[i] - nums[i-1] != 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        Card c = new Card();
        List<Card> result = c.randomSelect(5);
        c.printPork(result);
        c.judge(result);
    }
}