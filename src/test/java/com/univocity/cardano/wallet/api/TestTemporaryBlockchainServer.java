package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import org.testng.annotations.*;

public class TestTemporaryBlockchainServer {

/*   # create shelley network:
 *   mkdir /tmp/shelley
 *	 ./cardano-cli shelley genesis create --testnet-magic 777 --gen-genesis-keys 1 --gen-utxo-keys 1 --supply 1000000000 --genesis-dir /tmp/shelley/
 *
 *   # create BFT node:
 *   mkdir /tmp/node
 *
 *   # KES keys
 *   ./cardano-cli shelley node key-gen-KES --verification-key-file /tmp/node/kes.vkey --signing-key-file /tmp/node/kes.skey
 *
 *   # Operational certificate (VRF keys created automatically under /tmp/shelley/delegate-keys/)
 *   ./cardano-cli shelley node issue-op-cert --kes-verification-key-file /tmp/node/kes.vkey --cold-signing-key-file /tmp/shelley/delegate-keys/delegate1.skey --operational-certificate-issue-counter /tmp/shelley/delegate-keys/delegate1.counter --kes-period 0 --out-file /tmp/node/op.cert
 *
 *   ./cardano-node run \
      --config /home/jbax/dev/repository/envlp/cardano-wallet/src/test/resources/test-node-configuration.yaml \
      --topology /home/jbax/dev/repository/envlp/cardano-wallet/src/test/resources/test-node-topology.json \
      --database-path /tmp/node/db \
      --socket-path /tmp/node/node.sock \
      --shelley-kes-key /tmp/node/kes.skey \
      --shelley-vrf-key /tmp/shelley/delegate-keys/delegate1.vrf.skey \
      --shelley-operational-certificate /tmp/node/op.cert \
      --port 7777

     # Check node status
     export CARDANO_NODE_SOCKET_PATH=/tmp/node/node.sock
     ./cardano-cli shelley query protocol-parameters --testnet-magic 777 --shelley-mode

     # Check UTxO (TODO: addresses to be updated)
     ./cardano-cli shelley address build --payment-verification-key-file /tmp/shelley/utxo-keys/utxo1.vkey --testnet-magic 777
     ./cardano-cli shelley query utxo --testnet-magic 777 --shelley-mode --address addr_test1vz9vqpp232vpgh9w7m86qwy48a0e2c99thvqkp6jvatutecfngfmt

     # Transfer out of this first UTxO (gets a special hash):
     ./cardano-cli shelley genesis initial-txin --verification-key-file /tmp/shelley/utxo-keys/utxo1.vkey --testnet-magic 777

	 # Generate new keypair to receive $$
	 ./cardano-cli shelley address key-gen --verification-key-file /tmp/shelley/addr1.vkey --signing-key-file /tmp/shelley/addr1.skey

	 # Address to pay is:
	 ./cardano-cli shelley address build --payment-verification-key-file /tmp/shelley/addr1.vkey --testnet-magic 777

	 # Build transaction (TODO: addresses to be updated):
     ./cardano-cli shelley transaction build-raw \
      --tx-in  88226e24eb33d41836fd50a8643185ee81d100638b5de4a77aeb0229d8221389#0 \
      --tx-out addr_test1vpey5rkex4vzxad0du30sls8ry7jvjtrulrwynl4rx69ezgrl9n9a+1000000000 \
      --ttl 3600 \
      --fee 0 \
      --tx-body-file  /tmp/tx1.txbody

     # Sign:
       ./cardano-cli shelley transaction sign \
       --tx-body-file /tmp/tx1.txbody \
       --signing-key-file /tmp/shelley/utxo-keys/utxo1.skey \
       --testnet-magic 777 \
       --tx-file /tmp/tx1.tx

       ./cardano-cli shelley transaction submit \
       --tx-file /tmp/tx1.tx \
       --testnet-magic 777 \
       --shelley-mode

	 ./cardano-cli shelley address build --payment-verification-key-file /tmp/shelley-516af7d7-ba7e-41d6-886c-fe65fd9e20f5/payment.vkey --testnet-magic 6158513407646238242

	  export CARDANO_NODE_SOCKET_PATH=/tmp/shelley-516af7d7-ba7e-41d6-886c-fe65fd9e20f5/node/db/node.socket
*
     # Confirm payment went through (TODO: addresses to be updated):
      ./cardano-cli shelley query utxo \
      --testnet-magic 6158513407646238242 \
      --shelley-mode \
      --address addr_test1vpmj0wljksmrvwsdr6tmf39lt8wcyr37fy5707050pdcxdskpmy08
 */

	public static void main(String... args) throws InterruptedException {
		final String HOME = System.getProperty("user.home");
		final String DOWNLOADS = HOME + "/Downloads";

		EmbeddedWalletServer server = WalletServer.embedded()
				.binariesIn(DOWNLOADS + "/cardano-wallet-linux64/")
				.temporaryBlockchainNode()
				.port(5555)
				.consumeOutput(System.out::println)
				.wallet()
				.port(6666)
				.consumeOutput(System.out::println);

		server.start();
		server.waitForServerToStop();
	}
}
