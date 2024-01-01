"""
Python Data Structures - A Game-Based Approach
Stack class
Robin Andrews - https://compucademy.net/
"""


class Stack:
    def __init__(self):
        self.items = []

    def is_empty(self):
        #return len(self.items) == 0
        return not self.items
