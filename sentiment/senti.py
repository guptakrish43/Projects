import re
import tweepy, sys
from tweepy import OAuthHandler
from textblob import TextBlob
import matplotlib.pyplot as plt

def percentage(part, whole):
    return 100 * float(part)/float(whole)

def clean_tweet(tweet):
    return ' '.join(re.sub("(@[A-Za-z0-9]+)|([^0-9A-Za-z \t])|(\w+:\/\/\S+)", " ", tweet).split())

consumer_key = 'UiphDzKY7fobMgOXnbaVnZq9f'
consumer_secret = 'HNGqbMJOw7C5LGexey6d0xvXJBK6ujoJJtI127Wo45F3oHyC33'
access_token = '1103000721664823296-Pa7ZsnClNIXzc47z4709po85AqmbPn'
access_token_secret = 'r6cLwAr1PeQGpIp0JAubANE3H9HzknXLcQMp22i0Ebv7i'

try:
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    api = tweepy.API(auth)
except:
    print("Error: Authentication Failed")

searchTerm = input('Enter Keyword/hashtag to search about:')
noOfSearchTerms = int(input('Enter how many tweets to analyze:'))

try:
    tweets = api.search(q = searchTerm, count = noOfSearchTerms)
except tweepy.TweepError as e:
    print("Error : " + str(e))

positive = 0
negative = 0
neutral =  0
polarity = 0
a = 0
for value in tweets:
    print(value.text)
    a += 1
    analysis = TextBlob(clean_tweet(value.text))
    polarity += analysis.sentiment.polarity
    print(analysis.sentiment.polarity)
    if(analysis.sentiment.polarity == 0):
        neutral += 1
    elif(analysis.sentiment.polarity < 0.00):
        negative += 1
    elif(analysis.sentiment.polarity > 0.00):
        positive += 1

noOfSearchTerms = a
positive = percentage(positive ,noOfSearchTerms)
negative = percentage(negative ,noOfSearchTerms)
neutral = percentage(neutral ,noOfSearchTerms)
polarity = percentage(polarity ,noOfSearchTerms)

positive = format(positive,'.2f')
negative = format(negative,'.2f')
neutral = format(neutral,'.2f')

print("How many people are reacting on" + searchTerm + " by analyzing " + str(noOfSearchTerms) + "Tweets.")

if(polarity == 0.00):
    print("Neutral")
elif(polarity >0.00):
    print('Positive' )
elif(polarity < 0.00):
    print('Negative')

labels = ['Positive['+str(positive)+'%]','Neutral['+str(neutral)+'%]','Negative['+str(negative)+'%]']
sizes = [positive,neutral,negative]
colors = ['yellowgreen','gold','red']
patches,texts =plt.pie(sizes ,colors = colors,startangle=90)
plt.legend(patches ,labels ,loc='best')
plt.title('How people are reacting on '+ searchTerm +'by analyzing' +str(noOfSearchTerms) + 'tweets.')
plt.axis('equal')
plt.tight_layout()
plt.show()
