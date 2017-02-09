package maven.sortUtil;

import java.util.Random;

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
	 * 冒泡排序
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
	 * 选择排序
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
	 * 插入排序
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
	
	/**
	 * 归并排序的迭代法:
	 * 1.将序列每相邻两个数字进行归并操作,形成floor(n/2)个序列,排序后每个序列包含两个元素
	 * 2.将上述序列再次归并,形成floor(n/4)个序列,每个序列包含四个元素
	 * 3.重复步骤2,直到所有元素排序完毕
	 * @param arr
	 * @return
	 */
	public static int[] mergeSort_iteration(int[] arr){
		int len = arr.length;
		int[] temp = new int[len];//用于存放归并结果的临时数组
		int k = 1;//初始的子序列长度为1;
		while(k < arr.length){
			iteration(arr,temp,k,len);//将原先无序的数据两两归并入temp
			k*=2;//子序列的长度加倍
			iteration(temp,arr,k,len);//再将有序的temp两两归并入arr
			k*=2;//子序列的长度加倍
		}
		return arr;
	}
	
	/**
	 * 二路归并的第二种方法(一开始不开辟等长的数组,而是在每次归并的时候开辟数组)
	 * @param arr
	 * @return
	 */
	public static int[] mergeSort_iteration2(int[] arr){
		int k = 1;
		int low;
		int mid;
		int high;
		while(k <= arr.length - 1){
			//从第一个元素开始扫描,low代表第一个分割的第一个元素
			low = 0;
			//当前的归并算法结束的条件
			while(low + k < arr.length){
				//mid代表第一个分割的最后一个元素
				mid = low + k -1;
				//high代表第二个分割的最后一个元素
				high = mid + k;
				//判断一下,如果第二个分割的个数不足k个
				if(high > arr.length - 1){
					//调整high未数组的最后一个下标即可
					high = arr.length - 1;
				}	
				//调用归并函数进行归并排序
				merge(arr,low,mid,high);
				//下一次归并时第一个分割的第一个元素
				low = high + 1;
			}
			//范围扩大一倍
			k*=2;
		}
		return arr;
	}
	
	/**
	 * 将arr数组的数据归并入数组temp
	 * @param arr  源数组
	 * @param temp 存放归并结果的数组
	 * @param s 子序列的长度
	 * @param len 数组的总长度
	 */
	private static void iteration(int[] arr,int[] temp,int s,int len){
		int i = 0;//每一次归并的初始位置
		while(i < len-2*s - 1){
			merge(arr,temp,i,i+s-1,i+2*s-1);
			i+=2*s;
		}
		//处理最后的尾数
		if(i < len-s + 1){
			merge(arr,temp,i,i+s-1,len-1);//归并最后两个子序列
		}else{//若最后只剩下一个子序列
			for(int j = i ; j < len ; j++){
				temp[j] = arr[j];
			}
		}
	}
	
	/**
	 * 归并算法:将数组a从low到high的子序列归并到数组b的相应位置
	 * @param a
	 * @param b
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(int[] a,int[] b,int low,int mid,int high){
		int i = low;//左指针
		int j = mid + 1;//右指针
		int k = low;//记录数组b的开始位置
		while(i <= mid && j<=high){
			if(a[i] < a[j]){
				b[k] = a[i];
				i++;
				k++;
			}else{
				b[k] = a[j];
				j++;
				k++;
			}
		}
		while(i<=mid){
			b[k] = a[i];
			i++;
			k++;
		}
		while(j<=high){
			b[k] = a[j];
			j++;
			k++;
		}
	}
	
	/**
	 * 归并排序的递归法:
	 * 1.申请空间,使其大小为两个已经排序序列之和,该空间用来存放合并后的序列
	 * 2.设定两个指针,最初位置分别为两个已经排序序列的起始位置
	 * 3.比较两个指针所指向的元素,选择相对小的元素放入到合并空间,并移动指针到下一位置
	 * 4.重复步骤三3直到某一指针达到序列尾
	 * 5.将另一序列剩下的所有元素直接复制到合并序列尾
	 * @param arr
	 * @return
	 */
	public static int[] mergeSort_Recursion(int[] arr){
		int low = 0;
		int high = arr.length - 1;
		recursion(arr,low,high);
		return arr;
	}

	/**
	 * 递归算法
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void recursion(int[] arr,int low,int high){
		int mid = (low + high) / 2;//从最低位和最高位折半
		if(low < high){
			recursion(arr,low,mid);//左边
			recursion(arr,mid+1,high);//右边
			merge(arr,low,mid,high);//归并
		}
	}
	
	/**
	 * 归并算法:将两个有序序列合并成一个
	 * @param arr
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(int[] arr,int low,int mid,int high){
		int[] temp = new int[high-low+1];//临时数组
		int i = low;//左指针
		int j = mid + 1;//右指针
		int k = 0;//临时数组的初始位置
		//先把两边较小的元素插入临时数组
		while(i<=mid && j<=high){
			if(arr[i] < arr[j]){
				temp[k] = arr[i];
				i++;
				k++;
			}else{
				temp[k] = arr[j];
				j++;
				k++;
			}
		}
		//把左边剩余的元素插入临时数组
		while(i<=mid){
			temp[k] = arr[i];
			i++;
			k++;
		}	
		//把右边剩余的元素插入临时数组
		while(j<=high){
			temp[k] = arr[j];
			j++;
			k++;
		}
		//将临时数组的所有元素更新至待排序数组
		for(int r = 0 ; r < temp.length ; r++){
			arr[low + r] = temp[r];
		}
	}
	
	/**
	 * 快速排序的递归方法
	 * 1.从数列中挑出一个元素,成为基准(pivot)
	 * 2.重新排序数列,所有元素比基准值小的摆放在基准前面,所有比基准值大的摆放在基准的后面(相同的数可以到任一边),在这个分区退出之后,该基准就处于数列的中间位置.这个成为分区操作
	 * 3.递归地把小于基准值元素的子数列和大于基准值元素的子序列排序
	 * @return
	 */
	public static int[] quickSort_Recursion(int[] arr){
		partition(arr,0,arr.length-1);
		return arr;
	}
	
	/**
	 * 分区函数:将一个无序数组按照一个基准分成左边小于基准,右边大于基准的两个部分
	 * @param arr 待分区数组
	 * @param left 分区的开始位置
	 * @param right 分区的结束位置
	 */
	private static void partition(int[] arr,int left,int right){
		int i = left;//左指针
		int j = right;//右指针
		int pivot = arr[left];//基准
		int emptyIndex = i;//存放空位置的索引
		//当左右指针还没相遇时,循环
		while(i < j){
			//当左右指针还没相遇,并且右指针指向的元素的值比基准大时,继续扫描
			while(i < j && arr[j] >= pivot){
				//右指针向左移动一个位置
				j--;
			}
			//上面的循环跳出,说明右指针遇到了比基准小的元素,若此时左右指针还未相遇
			if(i < j){
				//把右指针指向的元素的值赋给空索引位置
				arr[emptyIndex] = arr[j];
				//将右指针的位置变为空索引位置
				emptyIndex = j;
			}
			//当左右指针还没相遇,并且左指针指向的元素的值比基准小时,继续扫描
			while(i < j && arr[i] <= pivot){
				//左指针向右移动一个位置
				i++;
			}
			//上面的循环跳出,说明左指针遇到了比基准大的元素,若此时左右指针还未相遇
			if(i < j){
				//把左指针指向的元素的值赋给空索引位置
				arr[emptyIndex] = arr[i];
				//将左指针的位置变为空索引位置
				emptyIndex = i;
			}
		}
		//跳出循环,完成一次分区操作.将基准元素放回空索引位置,
		arr[emptyIndex] = pivot;
		//如果左边的元素个数大于一
		if((i - left) > 1){
			//递归分区
			partition(arr, left, i-1);
		}
		//如果右边的元素个数大于一
		if((right - j) > 1){
			//递归分区
			partition(arr, j+1, right);
		}
	}
	
	public static void main(String[] args){
		int[] arr = new int[1000];
		Random random = new Random();
		for(int i = 0 ; i < 1000 ; i++){
			arr[i] = random.nextInt(1000);
		}
		
		long begin = System.currentTimeMillis();
		printArray(quickSort_Recursion(arr));
		long end = System.currentTimeMillis();
		System.out.println();
		System.out.println("快速排序用时:" + (end - begin));
		
		begin = System.currentTimeMillis();
		Sort.printArray(Sort.mergeSort_iteration(arr));
		end = System.currentTimeMillis();
		System.out.println();
		System.out.println("第一种二路归并排序用时:" + (end - begin));
		
		begin = System.currentTimeMillis();
		Sort.printArray(Sort.mergeSort_iteration2(arr));
		end = System.currentTimeMillis();
		System.out.println();
		System.out.println("第二种二路归并排序用时:" + (end - begin));
	}
}










