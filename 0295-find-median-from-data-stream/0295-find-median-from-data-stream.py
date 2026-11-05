import heapq
class MedianFinder:
    
    def __init__(self):
        self.left = []
        self.right = []
        
    def addNum(self, num: int) -> None:
        if (len(self.left) <= len(self.right)):
            heapq.heappush(self.left, -num)
        else:
            heapq.heappush(self.right, num)

        # balance/swap self.leftMax and self.rightMin if self.leftMax > self.rightMin
        if (self.left and self.right and -self.left[0] > self.right[0]):
            heapq.heappush(self.right, -heapq.heappop(self.left))
            heapq.heappush(self.left, -heapq.heappop(self.right))

    def findMedian(self) -> float:
        return -self.left[0] if (len(self.left) + len(self.right)) & 1 else (-self.left[0] + self.right[0]) / 2.0

        
        


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()