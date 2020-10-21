# cardano-wallet

This project is an open-source and pure Java client for the
official [cardano-wallet](https://github.com/input-output-hk/cardano-wallet), 
which is built and maintained by [Input|Output](https://iohk.io/).

## Introduction

The cardano-wallet consists of binaries that expose a REST api which allows
clients to perform common tasks on the cardano-blockchain, such as creating
wallets, submitting transactions, delegating to stake pools, etc.

This implementation aims to support the full [cardano-wallet api](https://input-output-hk.github.io/cardano-wallet/api/edge/)
including old Byron wallets, and to be compatible with not only Java but Android
and JVM-based programming languages such as Scala, Kotlin, etc.

Input|Output is also working to build its [cardano-wallet client](https://github.com/input-output-hk/psg-cardano-wallet-api)
but at the time of writing it is specific to the new Shelley functionality only,
and it's written in Scala with Akka which (as far as I know) makes it impossible to 
work with Android.

Our project also aims to provide an easy to use API for programmers, instead of
exposing the raw REST structure to you (but it's still accessible if you need). 

Finally, it helps you to build desktop wallet clients - like [Daedalus](https://daedaluswallet.io/) - with
embedded cardano-wallet binaries, so you don't necessarily have to 
connect to a remote cardano-wallet server.

## Warning

This library is still under development. Lots of functionalities are still only
accessible using classes that map to the raw API of the cardano-wallet. All of
these are meant to be used internally by a nicer-to use programming API which
is being built here.

## Installation

We are releasing new snapshots to maven central whenever there's any relevant
progress.

You can download the latest SNAPSHOT builds directly from [this link](https://oss.sonatype.org/content/repositories/snapshots/com/univocity/cardano-wallet/).

To get the snapshots automatically using maven, add this to the **repositories**
section of your `pom.xml`:

```xml
<repositories>

    <!-- other repositories ... -->

    <repository>
        <id>ossrh</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>
```

Then add the following dependency:

```xml
    <dependencies>

        <!-- other dependencies ... -->

        <dependency>
            <groupId>com.univocity</groupId>
            <artifactId>cardano-wallet</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

## Usage

To begin, start with a `WalletServer`. It allows you to connect to some
remote cardano-wallet service or to start with an embedded process.
 
### Connecting to a remote server:

The following snippet connects to a remote cardano-wallet server and query
the network parameters:

```java
RemoteWalletServer remoteServer = WalletServer
    .remote("http://your.server.com")
    .connectToPort(4444);

NetworkParameters networkParameters = remoteServer
    .network()
    .parameters();

System.out.println(networkParameters);
   
```

> **NOTE:** the example above does not use HTTPS, don't do this at home.

This will print out something like this:

```json
{
  "genesis_block_hash" : "5f20df933584822601f9e3f8c024eb5eb252fe8cefb24d1317dc3d432e940ebb",
  "blockchain_start_time" : "2017-09-23T21:44:51Z",
  "slot_length" : {
    "quantity" : 20.0,
    "unit" : "second"
  },
  "epoch_length" : {
    "quantity" : 21600,
    "unit" : "slot"
  },
  "epoch_stability" : {
    "quantity" : 2160,
    "unit" : "block"
  },
  "active_slot_coefficient" : {
    "quantity" : 100.0,
    "unit" : "percent"
  },
  "decentralization_level" : {
    "quantity" : 44.0,
    "unit" : "percent"
  },
  "desired_pool_number" : 150,
  "minimum_utxo_value" : {
    "quantity" : 1000000,
    "unit" : "lovelace"
  },
  "hardfork_at" : {
    "epoch_number" : 208,
    "epoch_start_time" : "2020-07-29T21:44:51Z"
  }
}
```

The `NetworkParameters` class makes your life a bit easier to extract information
from what comes out of the REST API. It offers a few helper methods that unwrap
the values you are interested in from the nested structures of JSON result
displayed above:
  
```java
networkParameters.formattedDecentralizationPercentage()
// Will print out `44.00%`
```

If you want to interact with the raw REST api directly, simply use:

```java
// Executes a synchronous (blocking) request:
GetNetworkParametersResponse response = remoteServer
    .api()
    .sync()
    .getNetworkParameters();

// Executes an asynchronous request:
remoteServer
    .api()
    .async()
    .getNetworkParameters(response -> {
        //do something with the GetNetworkParametersResponse response
    }     
);

```

The [full API](https://input-output-hk.github.io/cardano-wallet/api/edge/) of the cardano-wallet
is supported. We generate this code automatically so there's no reason to lag 
behind updates to the official cardano-wallet.

## Running an embedded cardano-wallet process

If you are interested in running the cardano-wallet binary along with your program,
just create an embedded wallet server.

Note that the `cardano-wallet` process connects to a `cardano-node` process
(cardano-node should be familiar to those who run stake pools).

To make this work you need to:

 1. download the [latest released](https://github.com/input-output-hk/cardano-wallet/releases) builds
from IOG (scroll down to the *assets* section and download the binaries for your platform).

 2. download the cardano-node configuration files [from here](https://hydra.iohk.io/build/4096235/download/1/index.html) 

> **NOTE:** currently only mainnet works, I didn't write the code to start up cardano-wallet on testnet (yet) 

> **NOTE(2):** only tested on Ubuntu linux. Hopefully it will work on Windows/Mac but I didn't try it yet.

Assuming you downloaded everything to your `Downloads` folder and unzipped the 
files there, you can start the local cardano-wallet server with: 

```java
final String HOME = System.getProperty("user.home");
final String DOWNLOADS = HOME + "/Downloads";

EmbeddedWalletServer server = WalletServer
    .embedded()
        .binariesIn(DOWNLOADS + "/cardano-wallet-2020.10.13/")
    .node()
        .configuration(DOWNLOADS + "/mainnet-config.json")
        .topology(DOWNLOADS + "/mainnet-topology.json")
        .storeBlockchainIn(DOWNLOADS + "/blockchain")
        .port(3333)
        .consumeOutput(System.out::println)
    .wallet()
        .port(4444)
        .consumeOutput(System.out::println);

NetworkClock clock = server.network().clock();
System.out.println(clock);
```

The `consumeOutput` calls above configures the `cardano-node`
and `cardano-wallet` processes to print their messages the system output. You can
redirect these to log files or simply ignore the output of these processes. 

Eventually, the network clock call will execute and print out something like:

```json
{
  "status" : "available",
  "offset" : {
    "quantity" : -562,
    "unit" : "microsecond"
  }
}
```

The program will exit soon after and automatically stop the `cardano-node` and
`cardano-wallet` services for you.

To keep the program running, and keeping printing the activity logs of the 
cardano-node and wallet processes, you can use something like this:   

```java
CardanoNodeManager nodeManager = server.getNodeManager();
nodeManager.waitForProcess();
```

This will make the program wait until the `cardano-node` process finishes 
(it never does).

Similarly, the `CardanoWalletManager` is also available to let you control the
process:

```java
CardanoWalletManager waletManager = server.getWalletManager();
waletManager.waitForProcess();
```

### NOTES:

For the cardano-wallet to work, the cardano-node has to be fully synced. 
On the first run this will take hours to complete. Make sure you have enough 
space available (~5GB) for the blockchain directory configured earlier. 

The `cardano-wallet` process will take a long time to retrieve the full list of
stake pools initially. This code will fail to run with `TimeOutException` a few
times until the cardano-wallet finally caches the result:

```java
List<StakePool> pools = remoteServer.stakePools().list(); 
```

## REST API code generation

All classes and methods that interact directly with the cardano-wallet REST API 
are generated automatically by class [ApiGenerator](https://github.com/uniVocity/cardano-wallet/blob/main/src/test/java/com/univocity/cardano/wallet/api/generator/ApiGenerator.java).

It simply reads the [swagger.yaml](https://github.com/input-output-hk/cardano-wallet/blob/master/specifications/api/swagger.yaml)
specification from IOG's cardano-wallet and generates all classes that conform to
that specification.

Every time IOG updates their spec, we can execute the `ApiGenerator` to update the
[generated classes](https://github.com/uniVocity/cardano-wallet/tree/main/src/main/java/com/univocity/cardano/wallet/api/generated)
and release a new build for you.

This allows us to quickly stay up to date and support EVERY single endpoint that
is made available on their API.

## TODO LIST
 
 * Finalize all code builders that wrap around the generated classes and hide the REST API from you.
 * Enable HTTPS on embedded mode.
 * Support testnet.
 * Ensure embedded server runs on Windows/Mac/Linux.
 * Document all source code files.
 * Automatically generate unit test code for REST API classes.
 * Download binaries and configuration files automatically.
 
## SUPPORTING OUR PROJECT

 * Delegate to the **SHOP** stake pool.
 * Or donate ADA to `addr1qy77cfgccmcfe9h936qunl4u36hyrwryrmzj6duug9sm73tdd0sqyslnjxvce9syyw4ktnrh0n7ct60zrs29wnef3jqq202748`

Thank you!