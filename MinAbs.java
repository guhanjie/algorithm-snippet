/**
 * 2015百度算法笔试题<br/>
 * <pre>
 * 有一个已经排序的数组（升序），数组中可能有正数、负数或0，求数组中元素的绝对值最小的数，
 * 要求不能用顺序比较的方法（复杂度需要小于O（n）），可以使用任何语言实现
 * </pre>
 * @author guhanjie
 * @time 2015-10-21 15:18:16
 */
public class MinAbs {
	public static void main(String[] args) {
		int[] arr = {-8,-6,-5,-4,-3,-2,-1,2,3};
		System.out.println(findMinAbs(arr));
		arr = new int[] {-3,-1,0,1,2,3,4,5,6};
		System.out.println(findMinAbs(arr));
		arr = new int[] {1,2,3,4,5,6,7,8};
		System.out.println(findMinAbs(arr));
	}

	/**
	 * 算法一：采用二分法，不断缩减空间，直至数组单向有序或者数组长度为2，最终得出结果
	 * @param  arr   [升序数组]
	 * @return       [数组中绝对值最小的数]
	 */
	public static int findMinAbs(int[] arr) {
		int start = 0;
		int end = arr.length-1;
		while(start < end) {
			if(arr[start] >= 0) {
				return arr[start];
			}
			if(arr[end] <= 0) {
				return arr[end];
			}
			if((end-start)==1) {
				return Math.abs(start) < Math.abs(end) ? arr[start] : arr[end];
			}
			int mid = (start+end)/2;
			if(arr[mid] < 0) {
				start = mid;
			}
			else if(arr[mid] > 0) {
				end = mid;
			}
			else {
				return 0;
			}
		}
		return arr[start];
	}

	/**
	 * 算法二：用三分法求升序数组data中绝对值最小的元素。如果有两个绝对值相等，返回值小的那一个。
	 * 摘自网络：<a href="http://www.debug4.me/Algorithm/ternary-search/">三分法求极值-Ternary Search</a>	 * 
	 * 与二分法的区别:
	 * 二分法适用场景是：从一个有序数组中寻找指定值的元素，适用于有序数组；
	 * 而三分法适用的是先升后降或者先降后升的数组。
	 * @param  data [升序数组]
	 * @return      [数组中绝对值最小的数]
	 */
	public static int findMinAbs2(int[] data) {
	    int left = 0;
	    int right = data.length - 1;
	    int m1 = left;
	    int m2 = right;
	    while (left < right) {
	        m1 = left + (right - left) / 3;
	        m2 = right - (right - left) / 3;
	        int f1 = Math.abs(data[m1]);
	        int f2 = Math.abs(data[m2]);
	        if (f1 > f2) {//缩小到区间(m1,right]
	            left = m1 + 1;
	        } else if (f1 < f2) {//缩小到区间[left,m2)
	            right = m2 - 1;
	        } else { //缩小到区间[m1,m2]
	            left = m1;
	            right = m2;
	            if (left == right - 1) {
	                break;//TODO这里是发现了两个最小值
	            }
	        }
	    }
	    return data[left];
	}
}
