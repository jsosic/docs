# AWS S3

## Signed urls

To test signed urls, you run:

```
$ docker run -it ruby:3 bash
root@480ddacf0c9c:/# export AWS_ACCESS_KEY_ID=AKIA...
root@480ddacf0c9c:/# export AWS_SECRET_ACCESS_KEY=xyz...
root@480ddacf0c9c:/# gem install aws-sdk-s3
root@480ddacf0c9c:/# irb
irb(main):001:0>
```

and copy/paste the following snippet:

```
require 'aws-sdk-s3'
aws_credentials = Aws::Credentials.new(ENV['AWS_ACCESS_KEY_ID'], ENV['AWS_SECRET_ACCESS_KEY']).credentials
Aws.config.update(region: 'us-east-1', credentials: aws_credentials)
Aws::S3::Resource.new.bucket('my-s3-bucket-name').object('path/to/object.pdf').presigned_url(:get, expires_in: 120)
```
