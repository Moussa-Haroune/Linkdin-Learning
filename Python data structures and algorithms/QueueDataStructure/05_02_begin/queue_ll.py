"""
Python Data Structures - A Game-Based Approach
Queue class
Robin Andrews - https://compucademy.net/
"""

from collections import deque


class Queue:
    def __init__(self):
        self.items = deque()

    def is_empty(self):
        return not self.items
        # return self.items == 0

    def enqueue(self, item):
        self.items.append(item)

    def dequeue(self):
        return self.items.popleft()

    def size(self):
        return len(self.items)

    def peek(self):
        return self.items[0]

    def __str__(self):
        return str(self.items)


if __name__ == '__main__':
    queue = Queue()
    print(queue)
    print(queue.is_empty())
    queue.enqueue("A")
    queue.enqueue("B")
    queue.enqueue("C")
    print(queue)
    print(queue.dequeue())
    print(queue.dequeue())
    print(queue)
    print(queue.size())
    print(queue.peek())
    print(queue)
