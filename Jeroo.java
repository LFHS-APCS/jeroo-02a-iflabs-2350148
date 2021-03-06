/**
 * Put your Jeroo methods in this class.
 * @author Steve Aronson
 */
public class Jeroo extends JerooBase {


    /**
     * when called, the Jeroo will finish facing east
     */
    public void faceEast()
    {
      if (isFacing(NORTH)) {
        this.turn(RIGHT);
      } else {
        if (isFacing(WEST)) {
          this.turn(LEFT);
          this.turn(LEFT);
        } else {
          if (isFacing(SOUTH)) {
            turn(LEFT);
          }
        }
      }
    }

    /**
     * return true if there is water to the left or one space away on the left
     * return false if there is not.
     * IMPORTANT:  Make sure the Jeroo is in its original spot and 
     *             facing its original direction when it finishes.
     */
    public boolean isLeftBlocked()
    {
        if (isClear(LEFT)) {
          this.turn(LEFT);
          this.hop();
          if (isClear(AHEAD)) {
            this.turn(LEFT);
            this.turn(LEFT);
            this.hop();
            this.turn(LEFT);
            return false;
          } else {
            this.turn(LEFT);
            this.turn(LEFT);
            this.hop();
            this.turn(LEFT);
            return true;
          }
        } else {
          return true;
        }
    }

    /**
     * Assume that a Jeroo has either 0, 1 or 2 flowers in front of it. 
     * If it is facing 2 flowers, it should end up turned to the right from its original position.
     * If it is facing less than 2 flowers, it should end up turned to the left from its original position.
     * In all cases, it should pick up the flowers.
     * IMPORTANT:  Other than its direction, the Jeroo should finish in its original spot.
     */
    public void findNextDirection()
    {
      if (isFlower(AHEAD)) 
      {
        this.hop();
        this.pick();
        if (isFlower(AHEAD))
        {
          this.hop();
          this.pick();
          this.turn(LEFT);
          this.turn(LEFT);
          this.hop();
          this.hop();
          this.turn(LEFT);

        } else {
          this.turn(LEFT);
          this.turn(LEFT);
          this.hop();
          this.turn(RIGHT);
        }
      } else {
        this.turn(LEFT);
      }
    }

    /**
     * Determine whether there is water on the left, right and front
     * return true if there is
     * return false if there is not
     * IMPORTANT:  Make sure the Jeroo is facing its original direction when it finishes
     *             However, if it is entirely surrounded by 4 waters including behind it
     *             then the Jeroo should turn around before returning true
     */
    public boolean isWayBlocked()
    {
      if (isWater(AHEAD)) {
        if (isWater(LEFT)) {
          if (isWater(RIGHT)) {
            this.turn(LEFT);
            this.turn(LEFT);
            if (isWater(AHEAD)) {
              return true;
            } else {
              this.turn(LEFT);
              this.turn(LEFT);
              return true;
            }
          } else {
            return false;
          }
        } else {
          return false;
        }
      } else {
        return false;
      }
    }


    /**
     * Put a flower in each enclosed room on the map that has water on 3 sides (front, left, right) 
     * The Jeroo starts at x = 0 and facing EAST.  Ideally, you should be calling the same helper method many times.
     */
    void roomCheck() {
      this.hop();
      if (isWater(LEFT)) {
        this.hop();
        this.turn(LEFT);
        this.hop();
        if (isWater(AHEAD)) {
          if (isWater(RIGHT)) {
            this.plant();
            this.turn(LEFT);
            this.turn(LEFT);
            this.hop();
            this.turn(LEFT);
          } else {
            this.turn(LEFT);
            this.turn(LEFT);
            this.hop();
            this.turn(LEFT);
          }
        } else {
            this.turn(LEFT);
            this.turn(LEFT);
            this.hop();
            this.turn(LEFT);
        }
      } else {
        ;
      }
    }
    
    public void carpetRoom()
    {
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
      this.roomCheck();
    }





    // Do NOT touch the code below here
    public Jeroo() {super();}

    public Jeroo(int flowers) {super(flowers); }

    public Jeroo(int y, int x) { super(y, x); }

    public Jeroo(int y, int x, CompassDirection direction) { super (y, x, direction);}

    public Jeroo(int y, int x, int flowers) { super (y, x, flowers);}

    public Jeroo(int y, int x, CompassDirection direction, int flowers) { super(y, x, direction, flowers);}
}
