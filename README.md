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
and it's written in Scala with Akka which as far as I know makes it impossible to 
work with Android.

Our project also aims to provide an easy to use API for programmers, instead of
exposing the raw REST structure to you (but it's still accessible if you need). 

Finally, it helps you to build desktop wallet clients - like [Daedalus](https://daedaluswallet.io/)
- with embedded cardano-wallet binaries, so you don't necessarily have to 
connect to a remote cardano-wallet server.

## Warning

This library is still under development. Lots of functionalities are still only
accessible using the raw API of the cardano-wallet. All of these are meant to
be used internally by a nicer-to use programming API.

## Installation

We are releasing new snapshots to maven central whenever there's any progress.

You download the latest SNAPSHOT builds directly from [this link](https://oss.sonatype.org/content/repositories/snapshots/com/univocity/cardano-wallet/).

To get the snapshots automatically using maven, add this to the **repositories** section of
your `pom.xml`:

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

To begin, you start with a `WalletServer`. It allows you to connect to some
remote cardano-wallet service or to start with an embedded process.
 

### Connecting to a remote server:

The following snippet connects to a remote cardano-wallet server and

```java
RemoteWalletServer remoteServer = WalletServer.remote("http://your.server.com").connectToPort(4444);

NetworkParameters networkParameters = remoteServer.network().parameters();
System.out.println(networkParameters);
   
```

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
    "quantity" : 0.0,
    "unit" : "percent"
  },
  "desired_pool_number" : 0,
  "minimum_utxo_value" : {
    "quantity" : 0,
    "unit" : "lovelace"
  },
  "hardfork_at" : null
}
```

> Note the example above does not use HTTPS, don't do this at home.

The `NetworkParameters` class makes your life a bit easier to extract information
from what comes out of the REST API by offering a few helper methods and unwrapping
the values you are interested in from the nested structures you get in the JSON result
displayed above:
  
```java
networkParameters.formattedDecentralizationPercentage()
```

Will print out `44.00%`

If you want to interact with the raw REST api directly, simply use:


```java
// Executes a synchronous (blocking) request:
GetNetworkParametersResponse response = remoteServer.api().sync().getNetworkParameters();

// Executes an asynchronous request:
remoteServer.api().async().getNetworkParameters(response -> {
    //do something with the GetNetworkParametersResponse response
});

```

The [full API](https://input-output-hk.github.io/cardano-wallet/api/edge/) of the cardano-wallet
is supported.

## Running an embedded cardano-wallet process

```java
final String HOME = System.getProperty("user.home");
final String CONFIGS = HOME + "/dev/repository/free-commerce/src/main/resources/";

EmbeddedWalletServer server = WalletServer.embedded()
    .toolsIn(HOME + "/dev/repository/free-commerce/src/main/resources/cli/lin")
    .node()
        .configuration(CONFIGS + "mainnet-config.json")
        .topology(CONFIGS + "mainnet-topology.json")
        .storeBlockchainIn(HOME + "/Downloads/blockchain")
        .port(3333)
        .consumeOutput(System.out::println)
    .wallet()
        .port(4444)
        .consumeOutput(System.out::println);
```