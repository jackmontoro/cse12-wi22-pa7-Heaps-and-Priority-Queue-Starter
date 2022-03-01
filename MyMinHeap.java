/**
 * TODO: Add your file header
 * Name:
 * ID:
 * Email:
 * Sources used: https://www.geeksforgeeks.org/min-heap-in-java/ ,Lecture Slides 
 * Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

// Your import statements
import java.util.*;
import java.io.*;
/**
 * Add class header
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{

    /**
     * TODO: Implement MinHeap
     */
    public ArrayList<E> data;
    private final int ZERO_INDEX = 0;
    private final int INV_INDEX = -1;

    public MyMinHeap(){
        this.data = new ArrayList<>();
    }

    public MyMinHeap(Collection<? extends E> collection){
        this.data = new ArrayList<>(collection);
        for(int i=this.size()-1;i>=ZERO_INDEX; i--){
            percolateDown(i);
        }
    }

    protected void swap(int from, int to){
        E temp = this.data.get(from);
        this.data.set(from,this.data.get(to));
        this.data.set(to,temp);
    }

    protected int getParentIdx(int index){
        return index / 2;
    }

    protected int getLeftChildIdx(int index){
        if(index == ZERO_INDEX){
            return 1;
        }
        return 2*index;
    }

    protected int getRightChildIdx(int index){
        if(index == ZERO_INDEX){
            return 2;
        }
        return (2*index) + 1;
    }

    protected int getMinChildIdx(int index){
        if(getLeftChildIdx(index)>this.data.size() ||
                getRightChildIdx(index)>this.data.size()){
                    return INV_INDEX;
                }
        int leftIdx = getLeftChildIdx(index);
        int rightIdx = getRightChildIdx(index);
        if(this.data.get(leftIdx).compareTo(data.get(rightIdx))<=0){
            return leftIdx;
        } else{
            return rightIdx;
        }
    }

    protected void percolateUp(int index){
        int parIdx = getParentIdx(index);
        E parentVal = this.data.get(parIdx);
        E childVal = this.data.get(index);
        if(index == ZERO_INDEX){
            this.data.set(index,childVal);
        }
        else if(parIdx == ZERO_INDEX){
            if(childVal.compareTo(parentVal)<0){
                swap(index,parIdx);
            }
        } else {
            if(childVal.compareTo(parentVal)<=0){
                swap(index,parIdx);
                percolateUp(parIdx);
            }
        }
    }

    protected void percolateDown(int index){
        int lftChldIdx = getLeftChildIdx(index);
        int rtChldIdx = getRightChildIdx(index);

        E currVal = this.data.get(index);

        while(true){
            if(lftChildIdx > this.size()-1){
                break;
            }

            E lftChild = this.data.get(rtChldIdx);
            int childIdx;

            if(lftChild == this.size()-1 ||
                    lftChild.compareTo(this.data.get(rtChldIdx)<=ZERO_INDEX)){
                childIdx = lftChldIdx;
            }
            else {
                childIdx = rtChldIdx;
            }

            E childVal = this.data.get(chldIdx);
            if(currVal.compareTo(childVal)<= 0){
                break;
            }
            else{
                swap(index,childIdx);
            }
        }
    }

    protected E deleteIndex(int index){
        int bottomRight = this.size()-1;
        this.data.set(index,this.data.get(bottomRight));
        E delNode = this.data.remove(bottomRight);

        percolateDown(index);
        return delNode;
    }

    public void insert(E element){
        if(element == null){
            throw new NullPointerException();
        }

        this.data.add(element);
        percolateUp(this.size()-1);
    }

    public E getMin(){
        return this.data.get(ZERO_INDEX);
    }

    public E remove(){
        return deleteIndex(ZERO_INDEX);
    }

    public int size(){
        return this.data.size();
    }

    public void clear(){
        this.data.clear();
    }
}