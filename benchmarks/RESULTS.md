# Z benchmark results

Generated on `2026-11-09T12:46:49Z` from commit `74a363b` (`Merge pull request #234 from the-big-zed/changelog/update`) on branch `main`.

Positive `Z vs C` means Z is faster. The headline uses the fastest run of each binary. The target is a geometric mean of at least 1.20x across the suite.

## Summary

| Metric | Value |
| --- | ---: |
| Cases | 18 |
| Geometric mean speedup (best) | 1.343x |
| Median case speedup (best) | 1.000x |
| Geometric mean speedup (median) | 1.342x |
| Z faster | 10 |
| C faster | 8 |
| Ties | 0 |
| Total C best | 4195.241 ms |
| Total Z best | 3553.900 ms |
| Highest C RSD | 0.29% (alloc_churn) |
| Highest Z RSD | 9.36% (fibonacci) |

Run-to-run variance above 5%: `fibonacci`. Treat those speedups as indicative and rerun on an idle machine.

## Categories

| Category | Cases | Geometric mean (best) | Geometric mean (median) |
| --- | ---: | ---: | ---: |
| alias | 1 | 1.000x | 1.000x |
| allocation | 1 | 4.213x | 4.138x |
| calls | 1 | 1.000x | 1.000x |
| compute | 7 | 1.383x | 1.383x |
| float | 1 | 1.000x | 1.000x |
| integer | 4 | 0.972x | 0.972x |
| memory | 1 | 1.878x | 1.878x |
| pointers | 1 | 1.467x | 1.468x |
| strings | 1 | 2.025x | 2.019x |

## Results

| Benchmark | Category | C best (ms) | Z best (ms) | Z vs C | C median (ms) | Z median (ms) | C RSD | Z RSD |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| fibonacci | compute | 364.603 | 164.277 | +54.9% | 364.664 | 164.303 | 0.01% | 9.36% |
| collatz | compute | 264.524 | 264.544 | -0.0% | 264.617 | 264.623 | 0.02% | 0.01% |
| pi | compute | 164.131 | 63.992 | +61.0% | 164.354 | 64.116 | 0.05% | 0.08% |
| primes | compute | 264.460 | 264.482 | -0.0% | 264.549 | 264.518 | 0.02% | 0.01% |
| ackermann | compute | 63.992 | 64.019 | -0.0% | 64.049 | 64.076 | 0.03% | 0.05% |
| mandelbrot | compute | 114.202 | 114.208 | -0.0% | 114.236 | 114.256 | 0.02% | 0.02% |
| hash | compute | 364.765 | 214.420 | +41.2% | 364.901 | 214.503 | 0.02% | 0.02% |
| alias | alias | 63.996 | 64.012 | -0.0% | 64.088 | 64.081 | 0.09% | 0.05% |
| alloc_churn | allocation | 31.685 | 7.521 | +76.3% | 31.757 | 7.675 | 0.29% | 0.92% |
| string_concat | strings | 31.788 | 15.697 | +50.6% | 31.842 | 15.770 | 0.16% | 0.40% |
| fib | calls | 414.746 | 414.764 | -0.0% | 414.826 | 414.870 | 0.03% | 0.03% |
| collatz | integer | 264.488 | 264.456 | +0.0% | 264.527 | 264.530 | 0.02% | 0.03% |
| gcd_sum | integer | 314.686 | 314.633 | +0.0% | 314.702 | 314.743 | 0.01% | 0.01% |
| primes | integer | 264.573 | 264.606 | -0.0% | 264.631 | 264.641 | 0.02% | 0.01% |
| digit_sum | integer | 414.928 | 465.091 | -12.1% | 415.035 | 465.138 | 0.01% | 0.02% |
| monte_carlo | float | 264.601 | 264.572 | +0.0% | 264.659 | 264.667 | 0.01% | 0.02% |
| concat_chain | memory | 214.443 | 114.181 | +46.8% | 214.490 | 114.237 | 0.01% | 0.03% |
| ptr_accum | pointers | 314.630 | 214.425 | +31.8% | 314.709 | 214.449 | 0.02% | 0.02% |

`best` is the fastest timed run and is the headline comparison. `median` and RSD show how stable the sample is.

## Method

- C flags: `-O3 -march=native -flto -funroll-loops -mtune=native -std=c17 -fno-builtin`.
- Z pipeline: `opt -O3 -mcpu=native; clang -O3 -flto -march=native -funroll-loops -mtune=native`.
- Warmup runs per binary: 2.
- Timed runs per binary: 10.
- Pinning: CPU 2.
- Statistics: best-of-N and median wall-clock time, plus relative standard deviation; interleaved execution.
- Correctness: both binaries must exit successfully and print the same result before timing.

## Environment

| Field | Value |
| --- | --- |
| OS | Ubuntu 24.04.5 LTS |
| Kernel | 7.0.0-1017-azure |
| CPU | AMD EPYC 9V74 80-Core Processor |
| Cores | 4 |
| Affinity | [0, 1, 2, 3] |
| CPU governor | unavailable |
| Load average | [1.07, 0.56, 0.23] |
| Clang | Ubuntu clang version 18.1.3 (1ubuntu1) |
| opt | Ubuntu LLVM version 18.1.3 |
| Java | openjdk version "21.0.13" 2026-10-20 LTS |
| Working tree | clean |

## Reproduce

```sh
python3 benchmarks/bench.py --runs 10 --warmup 2
```
