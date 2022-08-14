public class LinkedListDeque <Item>{
    private ItemNode sentinel;
    private  int size;
    public  class ItemNode{
        public ItemNode prev;
        public  Item item;
        public  ItemNode next;
        public  ItemNode(Item i){
            item=i;
        }
    }
    public  LinkedListDeque(){
        size=0;
        sentinel=new ItemNode(null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
    }
    public  LinkedListDeque(LinkedListDeque other){
        size=0;
        sentinel=new ItemNode(null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        for (int i=0;i< other.size();i++){
            addLast((Item) other.get(i));
        }
    }
    public void addFirst(Item x){
      ItemNode p=new ItemNode(x);
      p.next=sentinel.next;
      sentinel.next.prev=p;
      sentinel.next=p;
      p.prev=sentinel;
      size+=1;
    }
    public void addLast(Item x){
        ItemNode p=new ItemNode(x);
        p.prev=sentinel.prev;
        sentinel.prev.next=p;
        sentinel.prev=p;
        p.next=sentinel;
        size+=1;
    }
    public  int size(){
        return size;
    }
    public  boolean isEmpty(){
        if(sentinel.next==sentinel)
            return true;
        return  false;
    }
    public void  printDeque(){
        ItemNode p=sentinel.next;
        while(p.next!=sentinel){
            System.out.println(p.item+" ");
            p=p.next;
        }
        System.out.println("/n");
    }
    public Item removeFirst(){
        Item p=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        return p;
    }
    public Item removeLast(){
        Item p=sentinel.prev.item;
        sentinel.prev=sentinel.prev.prev;
        return p;
    }
    public Item get(int index){
        if(index>size){
            return  null;
        }
        ItemNode p=sentinel;
        for(int i=0;i<=index;i++){
            p=p.next;
        }
        return p.item;
    }
    public Item getRecursivehelper(int index,ItemNode start){
        if(index==0){
            return start.item;
        }
        return  getRecursivehelper(index-1,start.next);
    }
    public Item getRecursive(int index){
      if(index>size){
          return  null;
      }
      return  getRecursivehelper(index,sentinel.next);
    }
}
