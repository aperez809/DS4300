import random
import string
from datetime import datetime, timedelta
import numpy as np

class Tweet:

    # Template string to generate tweets from
    template = 'feijwgnrewignrf ewqoifn ewqof qowfn ewioqn dwodm sa,vsdvofdng rifneqwe1r1 re1o rne1r eoen fkwovn bovfowvnfow fvweov efwio vefinionw e oeo qq'

    # tweet_id value to increment with each tweet
    curr_tid = 1

    def __init__(self):
        self.tweet_id = Tweet.curr_tid; Tweet.curr_tid += 1
        self.user_id = random.randint(1,1000)

        # Current datetime with random offset of 0-20 days and 0-23 hours
        self.tweet_ts = datetime.now() + timedelta(days=random.randint(0, 20), hours=random.randint(0,23))
        self.tweet_text = ""

    def set_tweet_text(self):

        # Pick random start and end indexes to slice template string into a tweet
        start_end = sorted([random.randrange(0, 140), random.randrange(0, 140)])
        while start_end[1] - start_end[0] < 10:
            start_end = sorted([random.randrange(0, 140), random.randrange(0, 140)])
        self.tweet_text = f"{Tweet.template[start_end[0]:start_end[1]].strip()}\n"

class User:

    # user_id value to increment with each tweet
    curr_uid = 1

    # Generate semi-log distribution for user_followers
    # Many users have few followers and few have many
    follower_dist = np.logspace(0, 3, num=1010)

    def __init__(self):
        self.user_id = User.curr_uid; User.curr_uid += 1
        self.followers = set()

    def set_followers(self):

        # Assign each user `x` followers by using their user_id as an index key
        # to the `followers_dist` list
        num_followers = User.follower_dist[int(self.user_id) - 1]
        while len(self.followers) < num_followers:
            self.followers.add(random.randint(1,1000))

def generate_all_tweets():
    """
    Generate 1 million random Tweets and
    write them to a file called `tweets.txt`
    """
    with open('tweets.txt', 'w') as f:
        for i in range(10**6):
            curr_tweet = Tweet()
            curr_tweet.set_tweet_text()
            f.write(f"{curr_tweet.tweet_id},{curr_tweet.user_id},{curr_tweet.tweet_ts},{curr_tweet.tweet_text}")

def generate_user_followers():
    """
    Generate 1000 random Users and
    write them to a file called `user_followers.txt`
    """
    with open("user_followers.txt", "w") as f:
        for i in range(1000):
            curr_user = User()
            curr_user.set_followers()
            for follower in curr_user.followers:
                f.write(f"{curr_user.user_id},{follower}\n")

def main():
    generate_all_tweets()
    generate_user_followers()


main()