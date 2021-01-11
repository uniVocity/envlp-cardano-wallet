# envlp-cardano-wallet (sponsored by the SHOP stake pool)

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

The `cardano-wallet` backend built by IOG was not designed to be exposed as a 
public web service. The use case for it is close to 1 server <-> 1 client 
(or a few clients). If should be fine for utility APIs such as listing
stake pools, epoch information, among other things. Don't try creating and 
managing wallets if it's not running locally.

## Installation

You'll notice that the library version matches with the `cardano-wallet` releases
published by IOG [here](https://github.com/input-output-hk/cardano-wallet/releases).

This means that all endpoints available from that build of the official `cardano-wallet`
should be supported by the build you are installing. In general, it should be
safe to use more recent builds of the cardano-wallet as its API is generally stable
and won't change that often.

### Stable releases

Add the following dependency to your `pom.xml`:

(Snapshots available only for now, read next section)

```xml
    <dependencies>

        <!-- other dependencies ... -->

        <dependency>
            <groupId>com.univocity</groupId>
            <artifactId>envlp-cardano-wallet</artifactId>
            <version>2020.12.21-SNAPSHOT</version>
        </dependency>
    </dependencies>
```
 
### Snapshot builds

We are releasing new snapshots to maven central whenever there's any relevant
updates.

You can download the latest SNAPSHOT builds directly from [this link](https://oss.sonatype.org/content/repositories/snapshots/com/univocity/envlp-cardano-wallet/).

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

## Useful operations

### Seed phrases

```java
//seed phrase using 24 English words
String seed = Seed.generateEnglishSeedPhrase(24);
System.out.println(seed);

Output:
 >> swallow prefer target profit reopen minute state size isolate squirrel scrub table outer proud hint fire fossil behave clutch fragile juice weapon february cause
```

```java
//convert phrase into list of words
List<String> words = Seed.toMnemonicList(seed);
System.out.println(words);

Output:
 >> [swallow, prefer, target, profit, reopen, minute, state, size, isolate, squirrel, scrub, table, outer, proud, hint, fire, fossil, behave, clutch, fragile, juice, weapon, february, cause]
```

```java
//print entropy of seed phrase in hexadecimal
byte[] entropy = Seed.checkEnglishSeedPhrase(seed);
String entropyHex = Hex.encodeHexString(entropy);
System.out.println(entropyHex);

Output:
 >> db153b77d5eb671ab5364f769a77076e79d5599af2b95be28cb22e378df09511
```

```java
//restore or generate seed phrase from entropy
String seedFromEntropy = Seed.generateEnglishSeedPhrase(entropy);
System.out.println(seedFromEntropy);

Output:
 >> swallow prefer target profit reopen minute state size isolate squirrel scrub table outer proud hint fire fossil behave clutch fragile juice weapon february cause
```

### Wallet restoration


Create or restore a Shelley wallet:
```java
String seed = Seed.generateEnglishSeedPhrase(24);

ShelleyWallet wallet = server.wallets()
    .createOrGet("testing")
    .shelley()
    .fromSeed(seed)
    .password("qwerty12345")
```

Create or restore a Byron wallet:
```java
String seed = Seed.generateEnglishSeedPhrase(12);

ByronWallet wallet = server.wallets()
    .createOrGet("testing")
    .byron()
    .fromSeed(seed)
    .password("qwerty12345")
```

### Wallet operations:

List wallets:
```java
List<Wallet> wallets = server.wallets().list();
```

Rename wallet:
```java
wallet.rename("my new name");
```

Update wallet password:
```java
wallet.updatePassword("qwerty12345", "myNewPassword");
```

Delete wallet:
```java
wallet.delete();
```

### Addresses

List addresses (Shelley/Icarus/Ledger/Trezor):

```java
List<Address> addresses;
addresses = shelleyWallet.addresses().unused();
addresses = shelleyWallet.addresses().used();
addresses = shelleyWallet.addresses().all();

Address nextUnusedAddress = shelleyWallet.addresses().next();
```

List addresses (Byron):

```java

//byron wallet requires you to create addresses manually:
Address address1 = byronWallet.addresses().next("qwerty12345");
Address address2 = byronWallet.addresses().next("qwerty12345");
Address address3 = byronWallet.addresses().next("qwerty12345");

List<Address> addresses;
addresses = byronWallet.addresses().unused();
addresses = byronWallet.addresses().used();
addresses = byronWallet.addresses().all();

Address nextUnusedAddress = byronWallet.addresses().next();
```


### Balances

Wallet balances (in ADA):

```java
BigDecimal totalBalance = wallet.totalBalance();
BigDecimal availableBalance = wallet.availableBalance();
BigDecimal rewardsBalance = wallet.rewardsBalance();
```

### Transactions

Send money:

```java
String recipientAddress = "addr1qy77cfgccmcfe9h936qunl4u36hyrwryrmzj6duug9sm73tdd0sqyslnjxvce9syyw4ktnrh0n7ct60zrs29wnef3jqq202748";
BigDecimal amount = new BigDecimal("100000.54321")

ShelleyTransaction transaction = wallet.transfer()
    .to(recipientAddress, amount)
    .authorize("qwerty12345");

```

Fetch transaction:

```java
String transactionId = "2b1633ba62ee3bfc553f69579ae308614ed5c6a425844a6dbf4ba0629ab82ecf";
ShelleyTransaction transaction = shelleyWallet.transactions().get(transactionId);

String transactionId = "1b1644ba62ee3bfc653f6978ae308614ed5c6a425844a6dbf4ba0629ab82abc";
ByronTransaction transaction = byronWallet.transactions().get(transactionId);
```

List all wallet transactions:

```java
List<ShelleyTransaction> transactions = shelleyWallet.transactions().list();

List<ByronTransaction> byronTransactions = byronWallet.transactions().list();
```

List wallet transactions within a date range, asynchronously:

```java
LocalDateTime fromDate = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0);
LocalDateTime toDate = LocalDateTime.of(2020, Month.JUNE, 30, 23, 59);

Future<List<ByronTransaction>> transactionsLoading = byronWallet
                                                    .transactions()
                                                    .listAsync(fromDate, toDate);

List<ByronTransaction> transactions = transactionsLoading.get();
```

### Send payments to multiple addresses:

```java
Fees<ShelleyTransaction> fees = shelleyWallet
    .transfer()
    .to(firstWallet, new BigDecimal(15)) //15 ADA
    .and()
    .to("addr", new BigDecimal(111)) // 111 ADA
    .and()
    .to(byronWallet, new BigDecimal("222.55")) //222.55 ADA
    .estimateFees();

// you can estimate fees first 
BigDecimal fee = fees.maximumInAda();
System.out.println("Fee " + fee);

// then submit the transaction from there
ShelleyTransaction transaction = fees.authorize(PASSWORD);
```

### Metadata

#### Send a simple list of objects;

```java
ShelleyTransaction transaction = shelleyWallet.transfer()
    .to(someWallet, new BigDecimal(15))
    .withMetadata(new Object[]{"abcd", "efg", "hijk", 123})
    .authorize(PASSWORD)
```

Metadata has an index assigned to each entry, this will internally create 
a simple map with `{0=abcd, 1=efg, 2=hijk, 3=lmnop}`

#### Send a complex object structure

```java
Map<Long, Object> metadata = new TreeMap<>();
metadata.put(0L, "cardano");
metadata.put(1L, 14);
metadata.put(2L, new byte[]{55, 33, 33});

metadata.put(3L, Arrays.asList(14, 42, 1337));

Map<Object, Object> inner = new LinkedHashMap<>();
inner.put("key", "value");
inner.put(14, 42);
metadata.put(4L, inner);

ShelleyTransaction transaction = shelleyWallet.transfer()
    .to(someWallet, new BigDecimal(15))
    .withMetadata(metadata);
    .authorize(PASSWORD)
```

### Stake pools:

#### List stake pools

Notice, on the first run this will time out - the cardano-wallet backed takes a few minutes to load them all

```java
List<StakePool> pools = server.stakePools().listAsync().get();
```

#### Delegate to a stake pool

```java
StakePool pool = pools.get(0);
shelleyWallet.delegate(pool, PASSWORD);
```

To switch to another stake pool, simply execute the above with a new stake pool.

#### Update stake pool metadata source:

```java
server.stakePools().metadataSource("direct");
server.stakePools().metadataSource("none");
server.stakePools().metadataSource("https://some.smasth.server.url");
```

#### Force reload of stake pools

```java
server.stakePools().garbageCollect();
```

### Miscellaneous

#### Inspect addresses

```java
AddressDetails addressDetails = server.inspectAddress("addr1qysdgp6y6k5ncmw3ts4epnqv32tf762a5rlwcj0j5ek0vwawzdq65lps4pmtz6e5gwqsurydjrkc04vn82uwgsnpd0nsj4d8lh");
System.out.println(addressDetails.style()); //Shelley
System.out.println(addressDetails.stakeKeyHash()); //ae1341aa7c30a876b16b3443810e0c8d90ed87d5933ab8e442616be7
```


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
are generated automatically by class [ApiGenerator](https://github.com/uniVocity/envlp-cardano-wallet/blob/main/src/test/java/com/univocity/cardano/wallet/api/generator/ApiGenerator.java).

It simply reads the [swagger.yaml](https://github.com/input-output-hk/cardano-wallet/blob/master/specifications/api/swagger.yaml)
specification from IOG's cardano-wallet and generates all classes that conform to
that specification.

Every time IOG updates their spec, we can execute the `ApiGenerator` to update the
[generated classes](https://github.com/uniVocity/envlp-cardano-wallet/tree/main/src/main/java/com/univocity/cardano/wallet/api/generated)
and release a new build for you.

This allows us to quickly stay up to date and support EVERY single endpoint that
is made available on their API.

## TODO LIST
 
 * Ensure embedded server runs on Windows/Mac/Linux.
 * Document all source code files.
 * Download binaries and configuration files automatically.
 
## SUPPORTING OUR PROJECT

 * Delegate to the **SHOP** stake pool.
 * Or donate ADA to `addr1qy77cfgccmcfe9h936qunl4u36hyrwryrmzj6duug9sm73tdd0sqyslnjxvce9syyw4ktnrh0n7ct60zrs29wnef3jqq202748`

Thank you!