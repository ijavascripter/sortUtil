package maven.sortUtil;

public class Sort {
	
	/**
	 * 打印一个数组的所有元素
	 * @param arr
	 */
	public static void printArray(int[] arr){
		for(int i = 0 ; i < arr.length ; i++){
			System.out.print(arr[i] + ",");
		} 
	}
	
	/**
	 * 1.比较相邻的元素.如果第一个比第二个大,就交换他们两个.
	 * 2.对每一对相邻元素作同样的工作,从开始第一对到结尾的最后一对.这步做完之后,最后的元素会是最大的数.
	 * 3.针对所有的元素重复以上的步骤,除了最后一个.
	 * 4.持续每次对越来越少的元素重复上面的步骤,直到没有一对数字需要比较.
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSort(int[] arr){
		int temp = 0;//哨兵
		for(int i = 0 ; i < arr.length - 1 ; i++){//循环第i+1遍(总共n-1遍)
			for(int j = 0 ; j < arr.length - 1 - i ; j++){//每次循环需要处理的无需元素个数(n-1-i)
				if(arr[j] > arr[j+1]){
					//交换位置
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
	
	/**
	 * 1.首先在未排序序列中找到最小(大)元素,存放到排序序列的起始位置
	 * 2.再从剩余未排序元素中继续寻找最小(大)元素,然后放到已排序序列的末尾
	 * 3.重复第二步,直到所有元素均排序完毕
	 * @param arr
	 * @return
	 */
	public static int[] selectionSort(int[] arr){
		int minIndex = 0;//用于存放每次查找最值时,最值的位置
		int temp = 0;
		for(int i = 0 ; i < arr.length - 1 ; i++){//第i+1次寻找最小(大)元素,总共要寻找n-1次
			minIndex = i;
			for(int j = i + 1 ; j < arr.length ; j++){//从无序序列的开始位置寻找最值
				if(arr[j] < arr[minIndex]){
					minIndex = j;
				}
			}
			//交换位置,把最值存放到有序序列的末尾
			temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = temp;
		}
		return arr;
	}
	
	/**
	 * 1.将第一待排序序列第一个元素看做一个有序序列,把第二个元素到最后一个元素当成是未排序序列
	 * 2.从头到尾以此扫描未排序序列,将扫描到的每个元素插入到有序序列的适当位置.(如果待插入的元素与有序序列中的元素相等,则将待插入元素插入到相等元素的后面)
	 * @param arr
	 */
	public static int[] insertionSort(int[] arr){
		int current = 0;//用于存放待插入的元素
		int preIndex = 0;//用于存放插入位置索引
		for(int i = 1 ; i < arr.length ; i++){//从无序序列中依次拿出元素来插入有序序列中
			preIndex = i - 1;
			current = arr[i];//待插入的元素
			while(preIndex >= 0 && arr[preIndex] > current){
				arr[preIndex+1] = arr[preIndex];//元素向右移动一位
				preIndex--;//插入位置索引向前移动一位
			}
			arr[preIndex+1] = current;//插入元素
		}
		return arr;
	}
	public static void main(String[] args){
		int[] arr = {1,4,2,7,3,9,3,0,2,4,12,36,23,97,23,67,23,89,34,54,76,22,76};
		Sort.printArray(Sort.insertionSort(arr));
	}
}










