package ar.edu.itba.sia.g7.sokoban;

public class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point(Point aPoint) {
    this.x = aPoint.x;
    this.y = aPoint.y;
  }

  public int getX() { return x; }
  public int getY() { return y; }

  public Point add(Point aPoint) {
    return new Point(x + aPoint.x, y + aPoint.y);
  }

  public Point add(Direction direction) {
    return new Point(x + direction.getDeltaX(), y + direction.getDeltaY());
  }

  public Point add(int x, int y) {
    return new Point(this.x + x, this.y + y);
  }

  public Point sub(Point aPoint) {
    return new Point(x - aPoint.x, y - aPoint.y);
  }

  public int distance(Point aPoint) {
    Point diff = sub(aPoint);
    return Math.abs(diff.x) + Math.abs(diff.y);
  }

  public int hypotenuse(Point point){
    Point diff = sub(point);
    return (int) Math.ceil(Math.sqrt(Math.pow(point.x, 2) + Math.pow(point.y, 2)));
  }

  public boolean equals(Object o) {
    if (!(o instanceof Point)) { return false; }
    Point aPoint = (Point) o;
    return aPoint.x == x && aPoint.y == y;
  }

  public int hashCode() {
    return 31 * x + y;
  }

  @Override
  public String toString() {
    return "<Point x=" + x + " y=" + y + ">";
  }
}
