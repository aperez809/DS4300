import random
import string
from datetime import datetime, timedelta

class Tweet:

    template = 'feijwgnrewignrf ewqoifn ewqof qowfn ewioqn dwodm sa,vsdvofdng rifneqwe1r1 re1o rne1r eoen fkwovn bovfowvnfow fvweov efwio vefinionw e oeo qq'
    curr_tid = 1

    def __init__(self):
        self.tweet_id = Tweet.curr_tid; Tweet.curr_tid += 1
        self.user_id = random.randint(1,1000)
        self.tweet_ts = datetime.now() + timedelta(days=random.randint(0, 20), hours=random.randint(0,23))
        self.tweet_text = ""

    def set_tweet_text(self):
        start_end = sorted([random.randrange(0, 140), random.randrange(0, 140)])
        while start_end[1] - start_end[0] < 10:
            start_end = sorted([random.randrange(0, 140), random.randrange(0, 140)])
        self.tweet_text = f"{Tweet.template[start_end[0]:start_end[1]].strip()}\n"

def main():
    with open('tweets.txt', 'w') as f:
        for i in range(10**6):
            curr_tweet = Tweet()
            curr_tweet.set_tweet_text()
            f.write(f"{curr_tweet.tweet_id}, {curr_tweet.user_id}, {curr_tweet.tweet_ts}, {curr_tweet.tweet_text}")


