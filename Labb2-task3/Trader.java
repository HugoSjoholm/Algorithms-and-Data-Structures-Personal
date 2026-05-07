import java.io.*;

public class Trader extends Thread{
  private DataBuffer<StockPick> stockPicks;
  private int nrPicks; // nr of stock picks for printing to log-file each second
  private int endTime; // time to run in seconds

  public Trader(DataBuffer<StockPick> stockPicks,
                int bufferSize,
                int nrPicks,
                int endTime){
    this.stockPicks = stockPicks;
    this.nrPicks = nrPicks;
    this.endTime = endTime;
  }

  public void run(){
    try{
      OutputStreamWriter writer =
        new OutputStreamWriter(new FileOutputStream("log.txt"));
      writer.write("Start\n");
      writer.close();
    }
    catch(IOException e){}
    int time = 0;
    while (time < endTime){
      try{
        sleep(1000);
      }catch( InterruptedException e){}

      MaxPQ<StockPick> pq = new MaxPQ<StockPick>();

      while (!stockPicks.isEmpty()) {
          pq.insert(stockPicks.dequeue());
      }

      try {
          OutputStreamWriter writer =
              new OutputStreamWriter(new FileOutputStream("log.txt", true));

          int count = 0;

          while (count < nrPicks && !pq.isEmpty()) {
              writer.write(pq.delMax().toString());
              writer.write("\n");
              count++;
          }

          writer.close();
      }
      catch (IOException e) {
          System.out.println("Could not write to log.txt");
      }
      time++;
      System.out.println("Time elapsed: " + time + " seconds.");
      }
   }


    public static void main(String[] cmdLn){
        int bufferSize = 50;
        DataBuffer<StockPick> stockPicks =
              new DataBuffer<StockPick>(bufferSize);
        // StockPicker 1
        String[] stocks1 = {"TSLA", "CCJ", "GME",
                            "UUUU", "MFST", "GOOGL",
                            "AAPL", "AMZN"};

        StockPicker Stockpicker1 =
          new StockPicker(
            "North America analyzer",
            stockPicks,
            stocks1,
            10
          );


        // StockPicker 2
        String[] stocks2 = {"ETH", "BTC"};

        StockPicker Stockpicker2 =
          new StockPicker(
            "Cryptocurrencices analyzer",
            stockPicks,
            stocks2,
            10
          );

        // trader
        Trader trader =
        new Trader(stockPicks,
                   bufferSize,
                   3,
                   10);

        // run simulation
        Stockpicker1.start();
        Stockpicker2.start();
        trader.start();
      }
    }
