import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

  @Test
  public void testDistance(){
    Point a = new Point(2, 2);
    Point b = new Point(5, 6);

    Assert.assertEquals(Point.distance(a, b) , 5.00);
  }

}
