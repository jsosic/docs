# Google Compute Engine CLI 

## gcloud command

### Serial console

Physical servers usually have some kind of remote console, like KVM, iLO,
iDRAC ... Google offers `Virtual Serial Console`, which is currently still
in beta. It is not as powerfull as iDRAC and relatives, but it does help.

To access it, you need to enable it first:

```
gcloud compute instances add-metadata [INSTANCE_NAME] --metadata=serial-port-enable=1
```

Now, create files:

```
~/.ssh/google_compute_engine
~/.ssh/google_compute_engine.pub
```
These files represent keys that are set in project metadata for an user present over there.


Afterwards you can connect to it by running:

```
gcloud beta compute connect-to-serial-port [USER]@[INSTANCE_NAME]
```
