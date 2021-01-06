package com.univocity.cardano.wallet.api.generator;

import org.apache.commons.lang3.*;

import java.util.*;

public class CommonParent {
	static final Map<String, Set<String>> COMMON_PARENT = new HashMap<>();
	static final Map<String, String> PARENT_OF = new HashMap<>();

	static final Map<String, ClassRef> ABSTRACT_CLASSES = new HashMap<>();
	static final Set<String> KEEP_IN_ORIGINAL_PACKAGE = new HashSet<>();


	private static void put(String abstractClassName, String... classes) {
		if (abstractClassName.isEmpty()) {
			throw new IllegalStateException("Abstract class name can't be blank");
		}
		if (COMMON_PARENT.containsKey(abstractClassName)) {
			throw new IllegalStateException("Abstract class already defined: " + abstractClassName);
		}
		COMMON_PARENT.put(abstractClassName, new TreeSet<>(Arrays.asList(classes)));

		for (String child : classes) {
			if (PARENT_OF.containsKey(child)) {
				throw new IllegalStateException("Duplicate child class name: " + child);
			}
			PARENT_OF.put(child, abstractClassName);
		}
	}

	static {
		put("AbstractPercentage", "ActiveSlotCoefficient", "DecentralizationLevel", "Margin", "Progress", "RelativeStake");
		put("AbstractAmount", "Amount", "Available", "ControlledStake", "Cost", "EstimatedMax", "EstimatedMin", "Leftovers", "MigrationCost", "MinimumUtxoValue", "NonMyopicMemberRewards", "Pledge", "Reward", "Total", "Deposit", "Fee");
		put("AbstractSlotDetails", "InsertedAt","NodeTip","PendingSince","Tip");
		put("AbstractTimeDetails", "ExpiresAt","NetworkTip");
		put("AbstractLengthDetails", "SlotLength","TimeToLive");
		put("AbstractCoinSelectionRequest", "ByronSelectCoinsRequest", "PostByronTransactionFeeRequest", "SelectCoinsRequest");
		put("AbstractCoinSelectionResponse", "ByronSelectCoinsResponse", "SelectCoinsResponse");
		put("AbstractSchedule", "ChangesAt", "HardforkAt", "NextEpoch", "Retirement");
		put("AbstractAddress", "CreateAddressResponse", "ListAddressesResponseItem", "ListByronAddressesResponseItem");
		put("AbstractQuantity", "Depth", "EpochStability", "Height", "ProducedBlocks", "SecurityParameter");
		put("AbstractTransaction", "GetByronTransactionResponse", "GetTransactionResponse", "JoinStakePoolResponse", "ListByronTransactionsResponseItem", "ListTransactionsResponseItem", "MigrateByronWalletResponseItem", "MigrateShelleyWalletResponseItem", "PostByronTransactionResponse", "PostTransactionResponse");
		put("AbstractUTxOStatistic", "GetByronUTxOsStatisticsResponse", "GetUTxOsStatisticsResponse");
		put("AbstractWalletMigrationResponse", "GetByronWalletMigrationInfoResponse", "GetShelleyWalletMigrationInfoResponse");
		put("AbstractByronWalletResponse", "GetByronWalletResponse", "ListByronWalletsResponseItem", "PostByronWalletResponse");
		put("AbstractFeeResponse", "GetDelegationFeeResponse", "PostByronTransactionFeeResponse", "PostTransactionFeeResponse");
		put("AbstractWalletResponse", "GetWalletResponse", "ListWalletsResponseItem", "PostWalletResponse", "PutByronWalletResponse", "PutWalletResponse");
		put("AbstractInput", "Input", "InputsDelete");
		put("AbstractStakePoolRequest", "JoinStakePoolRequest", "QuitStakePoolRequest");
		put("AbstractPayment", "Output", "OutputsDelete", "Payment", "PaymentsPayment", "PaymentsRedemption");
		put("AbstractWalletFromPublicKey","PostByronWalletIcarusTrezorLedgerFromXpubRequest","PostWalletShelleyFromXpubRequest");
		put("AbstractPutWalletRequest", "PutByronWalletRequest", "PutWalletRequest");
		put("AbstractProgress", "State", "SyncProgress");
		put("AbstractWithdrawal", "Withdrawal", "WithdrawalsDelete");

		Collections.addAll(KEEP_IN_ORIGINAL_PACKAGE, "AbstractWalletResponse", "AbstractByronWalletResponse");
	}

	public static Collection<ClassRef> refactorToCommonParent(Collection<ClassRef> classes) {

		Set<ClassRef> classesToGenerate = new LinkedHashSet<>(classes);

		for (ClassRef ref : classes) {
			String parent = PARENT_OF.get(ref.name);
			if (parent != null) {
				ClassRef abstractClass = ABSTRACT_CLASSES.get(parent);
				if (abstractClass == null) {
					abstractClass = new ClassRef(parent, ref.classFile.getParentFile().toPath().resolve(parent + ".java").toFile());
					ABSTRACT_CLASSES.put(parent, abstractClass);

					String code = ref.code.toString();
					code = code.replace("public final class " + ref.name, "public abstract class " + parent);

					abstractClass.code.append(code);

					int docs = code.indexOf("/**");
					int codeStart = code.indexOf("public abstract class");

					abstractClass.code.delete(docs, codeStart);

					if (!KEEP_IN_ORIGINAL_PACKAGE.contains(parent)) {
						abstractClass.moveToCommonPackage();
					}

					classesToGenerate.add(abstractClass);
				}

				int start = ref.code.indexOf("public final class");
				int end = ref.code.indexOf("{", start) - 1;
				ref.code.setLength(end);
				ref.code.append(" extends ").append(parent).append(" {\n}\n");

				if (!KEEP_IN_ORIGINAL_PACKAGE.contains(parent)) {
					ref.importCommonPackage();
				}
			}
		}
		identifyClassesWithSameStructure(classesToGenerate);

		return classesToGenerate;
	}

	private static void identifyClassesWithSameStructure(Collection<ClassRef> classes) {
		Map<String, Set<String>> sameStructure = new TreeMap<>();
		for (ClassRef ref1 : classes) {
			for (ClassRef ref2 : classes) {
				if (ref1 != ref2) {
					if (ref1.isSameStructure(ref2)) {
						sameStructure.computeIfAbsent(ref1.name, n -> new TreeSet<>()).add(ref2.name);
						sameStructure.computeIfAbsent(ref2.name, n -> new TreeSet<>()).add(ref1.name);
					}
				}
			}
		}

		if (!sameStructure.isEmpty()) {
			System.err.println("The following classes share the same structure.");
			StringBuilder tmp = new StringBuilder();
			sameStructure.forEach((k, v) -> {
				v.add(k);

				if (v.size() > 1) {
					String commonSet = v.toString();
					commonSet = '"' + commonSet.substring(1, commonSet.length() - 1) + '"';
					commonSet = StringUtils.replace(commonSet, ", ", "\",\"");

					if (tmp.indexOf(commonSet) == -1) {
						tmp.append("\n\t\t").append("put(\"\",").append(commonSet).append(");");
					}

//					for(String s : v){
//						for (ClassRef ref1 : classes) {
//							if(ref1.name.equalsIgnoreCase(s)){
//								System.out.println(ref1.code);
//							}
//						}
//					}
				}
			});

			System.err.println(tmp);
			System.exit(0);
		}
	}
}
