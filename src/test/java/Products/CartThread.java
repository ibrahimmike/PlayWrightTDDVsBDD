package Products;

public class CartThread extends ThreadLocal{
    private static ThreadLocal<InitialCart> carts = new ThreadLocal<>();

    public static void setCart(){
        carts.set(new InitialCart());
    }
    public static InitialCart getInitialCart(){
        return carts.get();
    }
    public static void removeCarts(){
        carts.remove();
    }
}
