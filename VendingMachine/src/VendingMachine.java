import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

public class VendingMachine implements IVendingMachine {
    private Inventory<Coin> cashInventory = new Inventory<>();
    private Inventory<Item> itemInventory = new Inventory<>();
    private long totalSales;
    private Item currentItem;
    private long currentBalance;

    public void initialize(){
        for(Coin c: Coin.values()){
            cashInventory.put(c, 10);
        }

        for(Item i: Item.values()){
            itemInventory.put(i,5);
        }
    }

    @Override
    public long selectItemAndGetPrice(Item item) {
      if(itemInventory.hasItem(item)){
          currentItem = item;
          return currentItem.getPrice();
      }throw new SoldOutException("Item sold out, please select another item.");
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getDenomination();
        cashInventory.add(coin);
    }

    @Override
    public List<Coin> refund() {
        return null;
    }

    private List <Coin> getChange(Long amount) throws NotSufficientChangeException{
        List<Coin> changes = Collections.EMPTY_LIST;

        if(amount>0){
            changes = new ArrayList<>();
            long balance = amount;

            while(balance>0){
                if(balance>=Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER)){
                        changes.add(Coin.QUARTER);
                    balance = balance - Coin.QUARTER.getDenomination();
                    continue;
                }
                if(balance>=Coin.DIME.getDenomination() && cashInventory.hasItem(Coin.DIME)){
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getDenomination();
                    continue;
                }
                if(balance>=Coin.NICKLE.getDenomination() && cashInventory.hasItem(Coin.NICKLE)){
                    changes.add(Coin.NICKLE);
                    balance = balance - Coin.NICKLE.getDenomination();
                    continue;
                }
                if(balance>=Coin.PENNY.getDenomination() && cashInventory.hasItem(Coin.PENNY)){
                    changes.add(Coin.PENNY);
                    balance = balance - Coin.PENNY.getDenomination();
                    continue;
                }else {
                    throw new NotSufficientChangeException("No sufficient change, pleas try another item. ");
                }
            }
        }
        return changes;
    }

    @Override
    public Bucket<Item, List<Coin>> collectItemAndChange() {
        Item newItem = collectItem();
        totalSales = totalSales + currentItem.getPrice();

        List<Coin> change = collectChange();

        return new Bucket<>(newItem, change);
    }

    private Item collectItem() throws NotSufficientChangeException, NotFullPaidException{
            if (isFullPaid()) {
                if (hasSufficientChange()) {
                    itemInventory.deduct(currentItem);
                    return currentItem;
                }
                throw new NotSufficientChangeException("Not sufficient change in inventory. ");
            }
            long remainingBalance = currentItem.getPrice() - currentBalance;
            throw new NotFullPaidException("Price is not fully paid, remaining balabce: ", remainingBalance);
    }

    private List<Coin> collectChange(){
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    private boolean isFullPaid(){
        if(currentBalance>=currentItem.getPrice()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void reset() {
        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;

    }

    public void printStats(){
        System.out.println("Total sales: " + totalSales);
        System.out.println("Current Item Inventory: " + itemInventory);
        System.out.println("Current Cash Inventory: " + cashInventory);
    }

    private boolean hasSufficientChange(){
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount){
        boolean hasChange = true;
        try{
            getChange(amount);
        }catch(NotSufficientChangeException e){
            return hasChange = false;
        }
        return hasChange;
    }

    private void updateCashInventory(List<Coin> change){
        for(Coin c: change){
            cashInventory.deduct(c);
        }
    }

    public long getTotalSales() {
        return totalSales;
    }
}
